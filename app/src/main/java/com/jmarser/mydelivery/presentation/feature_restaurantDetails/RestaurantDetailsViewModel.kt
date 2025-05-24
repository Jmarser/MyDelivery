package com.jmarser.mydelivery.presentation.feature_restaurantDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jmarser.mydelivery.core.ErrorCodeState
import com.jmarser.mydelivery.domain.modelsDomain.DishItemDm
import com.jmarser.mydelivery.domain.modelsDomain.DishesListDm
import com.jmarser.mydelivery.domain.modelsDomain.RestaurantDm
import com.jmarser.mydelivery.domain.useCases.RestaurantDetailsUseCases
import com.jmarser.mydelivery.utilities.MyLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Project: My Delivery
 * File: RestaurantDetailsViewModel
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 20/05/2025
 */

@HiltViewModel
class RestaurantDetailsViewModel @Inject constructor(
    saveStateHandler: SavedStateHandle,
    private val useCase: RestaurantDetailsUseCases
) : ViewModel() {

    //private val restaurantId: String = checkNotNull(saveStateHandler["restaurantId"])
    private val restaurantId: String? = saveStateHandler["restaurantId"]


    private val _uiState = MutableStateFlow<RestaurantDetailsUiState>(RestaurantDetailsUiState.Idle)
    val uiState: StateFlow<RestaurantDetailsUiState> = _uiState.asStateFlow()

    private val _dishesUiState = MutableStateFlow<DishesUiState>(DishesUiState.Idle)
    val dishesUiState: StateFlow<DishesUiState> = _dishesUiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<RestaurantDetailsEffect>()
    val uiEffect: SharedFlow<RestaurantDetailsEffect> = _uiEffect.asSharedFlow()

    private val _favoritesDishes = MutableStateFlow<List<DishItemDm>>(emptyList())
    private var _listDishes: List<DishItemDm> = emptyList()

    init {
        if(restaurantId == null){
            viewModelScope.launch {
                _uiEffect.emit(RestaurantDetailsEffect.NavigateToBack)
            }
        }else {
            getRestaurantDetails()
            getDishesByResturant()
            observeFavoritesDishes()
        }
    }

    fun onEvent(event: RestaurantDetailsEvent) {
        when (event) {
            is RestaurantDetailsEvent.ToggleFavoriteRestaurant -> toggleFavoriteRestaurant(event.restaurant)
            is RestaurantDetailsEvent.ToggleFavoritesDishes -> toggleFavoritesDishes(event.dish)
            is RestaurantDetailsEvent.OnDishSelected -> dishSelected(event.dish)
            RestaurantDetailsEvent.OnRetry -> retry()
            RestaurantDetailsEvent.onNavigateToBack -> navigateToBack()
        }
    }

    private fun getRestaurantDetails() {
        _uiState.value = RestaurantDetailsUiState.Loading

        viewModelScope.launch {
            val details = flow {
                emit(useCase.getRestaurantDetailsUseCase.getRestaurantById(restaurantId!!))
            }

            val favorite = useCase.favoriteRestaurant.getByIdFlow(restaurantId!!)

            details.combine(favorite) { detailsUiState, favoriteRestaurant ->
                if (detailsUiState is RestaurantDetailsUiState.Success) {
                    val updated = detailsUiState.data.copy(
                        isFavorite = favoriteRestaurant != null
                    )
                    RestaurantDetailsUiState.Success(updated)
                } else {
                    detailsUiState
                }
            }.collect { combinedState ->
                _uiState.value = combinedState
            }
        }
    }

    private fun getDishesByResturant() {
        _dishesUiState.value = DishesUiState.Loading

        viewModelScope.launch {
            val result = useCase.getDishesByRestaurantUseCase.getDishesByRestaurant(restaurantId!!)

            if (result is DishesUiState.Success) {
                _listDishes = result.data.data.orEmpty().filterNotNull()
                combineDishesWithFavorites()
            } else {
                _dishesUiState.value = result
            }
        }
    }

    private fun toggleFavoriteRestaurant(restaurant: RestaurantDm) {
        viewModelScope.launch {
            useCase.favoriteRestaurant.toggleFavorite(restaurant)
        }
    }

    private fun toggleFavoritesDishes(dish: DishItemDm) {
        viewModelScope.launch {
            useCase.favoriteDishes.toggleFavorite(dish)
            combineDishesWithFavorites()
        }
    }

    private fun observeFavoritesDishes() {
        viewModelScope.launch {
            useCase.favoriteDishes.getFavorites().collectLatest { favorites ->
                _favoritesDishes.value = favorites
                combineDishesWithFavorites()
            }
        }
    }

    private fun combineDishesWithFavorites() {
        val favoritesIds = _favoritesDishes.value.mapNotNull { it.id }.toSet()

        val combined = _listDishes.map { dish ->
            dish.copy(isFavorite = favoritesIds.contains(dish.id))
        }

        _dishesUiState.value = if (combined.isEmpty()) {
            DishesUiState.Empty
        } else {
            DishesUiState.Success(DishesListDm(combined))
        }
    }

    private fun dishSelected(dish: DishItemDm){
        viewModelScope.launch {
            _uiEffect.emit(RestaurantDetailsEffect.NavigateToDishDetails(dish))
        }
    }

    private fun retry(){
        getRestaurantDetails()
        getDishesByResturant()
    }

    private fun navigateToBack(){
        viewModelScope.launch {
            _uiEffect.emit(RestaurantDetailsEffect.NavigateToBack)
        }
    }
}