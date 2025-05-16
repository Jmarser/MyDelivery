package com.jmarser.mydelivery.presentation.feature_home

import android.content.Context
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.delete
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmarser.mydelivery.core.ErrorCodeState
import com.jmarser.mydelivery.domain.modelsDomain.CategoryDm
import com.jmarser.mydelivery.domain.modelsDomain.RestaurantDm
import com.jmarser.mydelivery.domain.modelsDomain.RestaurantsListDm
import com.jmarser.mydelivery.domain.useCases.HomeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Project: My Delivery
 * File: HomeViewModel
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 06/05/2025
 */


@HiltViewModel
class HomeViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val useCase: HomeUseCases
) : ViewModel() {

    val searchTextState = TextFieldState()
    private var allRestaurants: List<RestaurantDm> = emptyList()

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<HomeEffect>()
    val uiEffect: SharedFlow<HomeEffect> = _uiEffect.asSharedFlow()

    private val _categoryUiState = MutableStateFlow<CategoriesUiState>(CategoriesUiState.Idle)
    val categoryUiState: StateFlow<CategoriesUiState> = _categoryUiState.asStateFlow()

    private val _restaurentsUiState = MutableStateFlow<RestaurantsUiState>(RestaurantsUiState.Idle)
    val restaurantsUiState: StateFlow<RestaurantsUiState> = _restaurentsUiState.asStateFlow()

    private val _favoritesRestaurants = MutableStateFlow<List<RestaurantDm>>(emptyList())

    init {
        getAllCategories()
        getRestaurants()
        getAllRestaurantsFavorites()
        observeSearchQuery()
        observeFavoritesChanges()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnCategorySelected -> categorySelected(event.category)
            is HomeEvent.OnRestaurantSelected -> restaurantSelected(event.restaurant)
            HomeEvent.ClearSearchQuery -> clearSearchQuery()
            is HomeEvent.ToggleFavoriteRestaurant -> toggleFavoriteRestaurant(event.restaurant)
        }
    }

    private fun getAllCategories() {
        _categoryUiState.value = CategoriesUiState.Loading

        viewModelScope.launch {
            val result = useCase.getAllCategories()

            _categoryUiState.value = result
        }
    }

    private fun getRestaurants() {
        _restaurentsUiState.value = RestaurantsUiState.Loading

        viewModelScope.launch {
            val result = useCase.getRestaurants(
                lat = 40.712776,
                lon = -74.005978
            )

            if (result is RestaurantsUiState.Success) {
                allRestaurants = result.data.data
                applyFilters()
            } else {
                _restaurentsUiState.value = result
            }
        }
    }

    private fun getRestaurantsByCategory(category: CategoryDm) {
        _restaurentsUiState.value = RestaurantsUiState.Loading

        if (category.id != null) {
            viewModelScope.launch {
                val result = useCase.getRestaurantsByCategory(
                    lat = 40.712776,
                    lon = -74.005978,
                    categoryId = category.id
                )

                if (result is RestaurantsUiState.Success) {
                    allRestaurants = result.data.data
                    applyFilters()
                } else {
                    _restaurentsUiState.value = result
                }
            }
        } else {
            _restaurentsUiState.value =
                RestaurantsUiState.Failure(errorCodeState = ErrorCodeState.RESOURCE_NOT_FOUND)
        }
    }

    private fun categorySelected(category: CategoryDm) {

        val currentCategory = _uiState.value.selectedCategory

        if (currentCategory?.id == category.id) {
            _uiState.update { it.copy(selectedCategory = null) }
            getRestaurants()
        } else {
            _uiState.update { it.copy(selectedCategory = category) }
            getRestaurantsByCategory(category)
        }

        viewModelScope.launch {
            _uiEffect.emit(HomeEffect.CategorySelected(category))
        }
    }

    private fun restaurantSelected(restaurant: RestaurantDm) {
        _uiState.update { it.copy(selectedRestaurant = restaurant) }

        viewModelScope.launch {
            _uiEffect.emit(HomeEffect.RestaurantSelected(restaurant))
        }
    }

    private fun clearSearchQuery() {
        searchTextState.edit { delete(0, length) }
    }

    private fun observeSearchQuery() {
        viewModelScope.launch {
            snapshotFlow { searchTextState.text.toString() }
                .debounce(300L)
                .distinctUntilChanged()
                .collectLatest { query ->
                    _uiState.update { it.copy(searchQuery = query) }
                    applyFilters()
                }
        }
    }

    private fun applyFilters(){
        val query = _uiState.value.searchQuery
        val selectedCategory = _uiState.value.selectedCategory
        val favoritesRestaurants = _favoritesRestaurants.value
        val frIds = favoritesRestaurants.map { it.id }.toSet()

        val filtered = allRestaurants
            .asSequence()
            .filter { restaurant ->
                selectedCategory?.id?.let{catId ->
                    restaurant.categoryId == catId
                }?: true
            }
            .filter { restaurant ->
                restaurant.name?.contains(query, ignoreCase = true) ?: true
            }
            .map { restaurant ->
                restaurant.copy(isFavorite = frIds.contains(restaurant.id))
            }
            .toList()

        _restaurentsUiState.value = if (filtered.isEmpty()){
            RestaurantsUiState.Empty
        }else{
            RestaurantsUiState.Success(RestaurantsListDm(filtered))
        }
    }


    private fun toggleFavoriteRestaurant(restaurant: RestaurantDm){
        viewModelScope.launch {
            useCase.favoriteRestaurant.toggleFavorite(restaurant)
        }
    }

    private fun getAllRestaurantsFavorites(){
        viewModelScope.launch {
            useCase.favoriteRestaurant.getFavorites().collect{favorites ->
                _favoritesRestaurants.value = favorites
            }
        }
    }

    private fun observeFavoritesChanges(){
        viewModelScope.launch {
            _favoritesRestaurants.collect{
                applyFilters()
            }
        }
    }
}