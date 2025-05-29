package com.jmarser.mydelivery.presentation.feature_dishDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmarser.mydelivery.domain.modelsDomain.DishItemDm
import com.jmarser.mydelivery.domain.useCases.DishDetailsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Project: My Delivery
 * File: DishDetailsViewModel
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 28/05/2025
 */

@HiltViewModel
class DishDetailsViewModel @Inject constructor(
    savedStateHandler: SavedStateHandle,
    private val useCase: DishDetailsUseCases
): ViewModel(){

    private val dishId: String? = savedStateHandler["dishId"]

    private val _uiState = MutableStateFlow<DishDetailsUiState>(DishDetailsUiState.Idle)
    val uiState: StateFlow<DishDetailsUiState> = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<DishDetailsEffect>()
    val uiEffect: SharedFlow<DishDetailsEffect> = _uiEffect.asSharedFlow()

    init {

        if(dishId == null){
            viewModelScope.launch {
                _uiEffect.emit(DishDetailsEffect.NavigateToBack)
            }
        }else {
            getDishDetails()
        }
    }

    fun onEvent(event: DishDetailsEvent){
        when(event){
            DishDetailsEvent.OnNavigateToBack -> navigateToBack()
            DishDetailsEvent.OnRetry -> retry()
            is DishDetailsEvent.ToggleFavoriteDish -> toggleFavoriteDish(event.dish)
        }
    }

    private fun getDishDetails(){
        _uiState.value = DishDetailsUiState.Loading

        viewModelScope.launch {
            val details = flow {
                emit(useCase.getDishById.getDishById(dishId!!))
            }

            val favorite = useCase.favoriteDish.getByIdFlow(dishId!!)

            details.combine(favorite){detailsUiState, favoriteDish ->
                if(detailsUiState is DishDetailsUiState.Success){
                    val updated = detailsUiState.data.copy(
                        isFavorite = favoriteDish != null
                    )
                    DishDetailsUiState.Success(updated)
                }else{
                    detailsUiState
                }
            }.collect{combinedState ->
                _uiState.value = combinedState
            }



            val response = useCase.getDishById.getDishById(dishId)

            _uiState.value = response
        }
    }

    private fun navigateToBack(){
        viewModelScope.launch {
            _uiEffect.emit(DishDetailsEffect.NavigateToBack)
        }
    }

    private fun retry(){
        _uiState.value = DishDetailsUiState.Idle
        getDishDetails()
    }

    private fun toggleFavoriteDish(dish: DishItemDm){
        viewModelScope.launch {
            useCase.favoriteDish.toggleFavorite(dish)
        }
    }
}