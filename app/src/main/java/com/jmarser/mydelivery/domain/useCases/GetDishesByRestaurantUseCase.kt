package com.jmarser.mydelivery.domain.useCases

import com.jmarser.mydelivery.core.ErrorCodeState
import com.jmarser.mydelivery.data.mappers.toDomain
import com.jmarser.mydelivery.data.remote.repository.Resource
import com.jmarser.mydelivery.domain.repository.HomeRepository
import com.jmarser.mydelivery.presentation.feature_restaurantDetails.DishesUiState
import javax.inject.Inject

/**
 * Project: My Delivery
 * File: GetDishesByRestaurantUseCase
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 21/05/2025
 */

class GetDishesByRestaurantUseCase @Inject constructor(
    private val repo: HomeRepository
) {

    suspend fun getDishesByRestaurant(restaurantId: String): DishesUiState{
        when(val response = repo.getDishesByRestaurant(restaurantId)){
            is Resource.Success -> {
                response.value.let {
                    return DishesUiState.Success(data = it.toDomain())
                }
            }
            is Resource.Failure -> {
                return DishesUiState.Failure(
                    isNetwork = response.isNetworkError,
                    errorCodeState = response.errorCodeState,
                    message = response.errorMessage
                )
            }
            else -> return DishesUiState.Failure(errorCodeState = ErrorCodeState.UNKNOWN_ERROR)
        }
    }
}