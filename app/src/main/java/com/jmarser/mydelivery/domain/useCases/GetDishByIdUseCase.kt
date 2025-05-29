package com.jmarser.mydelivery.domain.useCases

import com.jmarser.mydelivery.core.ErrorCodeState
import com.jmarser.mydelivery.data.mappers.toDomain
import com.jmarser.mydelivery.data.remote.repository.Resource
import com.jmarser.mydelivery.domain.repository.HomeRepository
import com.jmarser.mydelivery.presentation.feature_dishDetails.DishDetailsUiState
import javax.inject.Inject

/**
 * Project: My Delivery
 * File: GetDishByIdUseCase
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 28/05/2025
 */

class GetDishByIdUseCase @Inject constructor(
    private val repo: HomeRepository
) {

    suspend fun getDishById(dishId: String): DishDetailsUiState{
        when(val response = repo.getDishById(dishId)){
            is Resource.Success -> {
                response.value.data?.let {
                    return DishDetailsUiState.Success(data = it.toDomain())
                } ?: return DishDetailsUiState.Empty
            }
            is Resource.Failure -> {
                return DishDetailsUiState.Failure(
                    isNetwork = response.isNetworkError,
                    errorCodeState = response.errorCodeState,
                    message = response.errorMessage
                )
            }
            else -> return DishDetailsUiState.Failure(errorCodeState = ErrorCodeState.UNKNOWN_ERROR)
        }
    }
}