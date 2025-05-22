package com.jmarser.mydelivery.domain.useCases

import com.jmarser.mydelivery.core.ErrorCodeState
import com.jmarser.mydelivery.data.mappers.toDomain
import com.jmarser.mydelivery.data.remote.repository.Resource
import com.jmarser.mydelivery.domain.repository.HomeRepository
import com.jmarser.mydelivery.presentation.feature_restaurantDetails.RestaurantDetailsUiState
import javax.inject.Inject

/**
 * Project: My Delivery
 * File: GetRestaurantByIdUseCase
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 20/05/2025
 */

class GetRestaurantByIdUseCase @Inject constructor(
    private val repo: HomeRepository
) {

    suspend fun getRestaurantById(restaurantId: String): RestaurantDetailsUiState{
        when(val response = repo.getRestaurantById(restaurantId)){
            is Resource.Success -> {
                response.value.data?.let {
                    return RestaurantDetailsUiState.Success(data = it.toDomain())
                } ?: return RestaurantDetailsUiState.Empty
            }
            is Resource.Failure -> {
                return RestaurantDetailsUiState.Failure(
                    isNetwork = response.isNetworkError,
                    errorCodeState = response.errorCodeState,
                    message = response.errorMessage
                )
            }
            else -> return RestaurantDetailsUiState.Failure(errorCodeState = ErrorCodeState.UNKNOWN_ERROR)
        }
    }
}