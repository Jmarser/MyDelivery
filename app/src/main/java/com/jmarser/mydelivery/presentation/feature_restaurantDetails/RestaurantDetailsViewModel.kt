package com.jmarser.mydelivery.presentation.feature_restaurantDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmarser.mydelivery.domain.modelsDomain.RestaurantDm
import com.jmarser.mydelivery.domain.useCases.RestaurantDetailsUseCases
import com.jmarser.mydelivery.utilities.MyLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
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
): ViewModel(){

    private val restaurantId: String = checkNotNull(saveStateHandler["restaurantId"])


    private val _uiState = MutableStateFlow<RestaurantDetailsUiState>(RestaurantDetailsUiState.Idle)
    val uiState: StateFlow<RestaurantDetailsUiState> = _uiState.asStateFlow()

    private val _dishesUiState = MutableStateFlow<DishesUiState>(DishesUiState.Idle)
    val dishesUiState: StateFlow<DishesUiState> = _dishesUiState.asStateFlow()


    init{
        getRestaurantDetails()
        getDishesByResturant()
    }


    private fun getRestaurantDetails(){
        _uiState.value = RestaurantDetailsUiState.Loading

        viewModelScope.launch {
            val result = useCase.getRestaurantDetailsUseCase.getRestaurantById(restaurantId)

            _uiState.value = result
        }
    }

    private fun getDishesByResturant(){
        _dishesUiState.value = DishesUiState.Loading

        viewModelScope.launch {
            val result = useCase.getDishesByRestaurantUseCase.getDishesByRestaurant(restaurantId)
            _dishesUiState.value = result
        }
    }

}