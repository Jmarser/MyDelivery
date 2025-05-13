package com.jmarser.mydelivery.presentation.feature_home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmarser.mydelivery.core.ErrorCodeState
import com.jmarser.mydelivery.domain.modelsDomain.CategoryDm
import com.jmarser.mydelivery.domain.modelsDomain.RestaurantDm
import com.jmarser.mydelivery.domain.useCases.HomeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
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
): ViewModel(){

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<HomeEffect>()
    val uiEffect: SharedFlow<HomeEffect> = _uiEffect.asSharedFlow()

    private val _categoryUiState = MutableStateFlow<CategoriesUiState>(CategoriesUiState.Idle)
    val categoryUiState: StateFlow<CategoriesUiState> = _categoryUiState.asStateFlow()

    private val _restaurentsUiState = MutableStateFlow<RestaurantsUiState>(RestaurantsUiState.Idle)
    val restaurantsUiState: StateFlow<RestaurantsUiState> = _restaurentsUiState.asStateFlow()

    init {
        getAllCategories()
        getRestaurants()
    }

    fun onEvent(event: HomeEvent){
        when(event){
            is HomeEvent.OnCategorySelected -> categorySelected(event.category)
            is HomeEvent.OnRestaurantSelected -> restaurantSelected(event.restaurant)
        }
    }

    private fun getAllCategories(){
        _categoryUiState.value = CategoriesUiState.Loading

        viewModelScope.launch {
            val result = useCase.getAllCategories()

            _categoryUiState.value = result
        }
    }

    private fun getRestaurants(){
        _restaurentsUiState.value = RestaurantsUiState.Loading

        viewModelScope.launch {
            val result = useCase.getRestaurants(
                lat = 40.712776,
                lon = -74.005978
            )

            _restaurentsUiState.value = result
        }
    }

    private fun getRestaurantsByCategory(category: CategoryDm){
        _restaurentsUiState.value = RestaurantsUiState.Loading

        if (category.id != null) {
            viewModelScope.launch {
                val result = useCase.getRestaurantsByCategory(
                    lat = 40.712776,
                    lon = -74.005978,
                    categoryId = category.id
                )

                _restaurentsUiState.value = result
            }
        }else{
            _restaurentsUiState.value = RestaurantsUiState.Failure(errorCodeState = ErrorCodeState.RESOURCE_NOT_FOUND)
        }
    }

    private fun categorySelected(category: CategoryDm){

        val currentCategory = _uiState.value.selectedCategory

        if (currentCategory?.id == category.id){
            _uiState.update { it.copy(selectedCategory = null) }
            getRestaurants()
        }else{
            _uiState.update { it.copy(selectedCategory = category) }
            getRestaurantsByCategory(category)
        }

        viewModelScope.launch {
            _uiEffect.emit(HomeEffect.CategorySelected(category))
        }
    }

    private fun restaurantSelected(restaurant: RestaurantDm){
        _uiState.update { it.copy(selectedRestaurant = restaurant) }

        viewModelScope.launch {
            _uiEffect.emit(HomeEffect.RestaurantSelected(restaurant))
        }
    }
}