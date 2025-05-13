package com.jmarser.mydelivery.domain.useCases

import com.jmarser.mydelivery.core.ErrorCodeState
import com.jmarser.mydelivery.data.mappers.toListRestaurantsDomain
import com.jmarser.mydelivery.data.remote.repository.Resource
import com.jmarser.mydelivery.domain.repository.HomeRepository
import com.jmarser.mydelivery.presentation.feature_home.RestaurantsUiState
import javax.inject.Inject

/**
 * Project: My Delivery
 * File: GetRestaurantsUseCase
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 13/05/2025
 */

class GetRestaurantsUseCase @Inject constructor(
    private val repo: HomeRepository
) {
    suspend operator fun invoke(lat: Double, lon: Double): RestaurantsUiState{
        when (val response = repo.getRestaurants(lat, lon)){
            is Resource.Success -> {
                val body = response.value
                val listRestaurants = body.toListRestaurantsDomain()

                return if (listRestaurants.data.isEmpty()){
                    RestaurantsUiState.Empty
                }else{
                    listRestaurants.let {
                        RestaurantsUiState.Success(data = it)
                    }
                }
            }
            is Resource.Failure -> {
                return RestaurantsUiState.Failure(
                    isNetwork = response.isNetworkError,
                    errorCodeState = response.errorCodeState,
                    message = response.errorMessage
                )
            }
            else -> return RestaurantsUiState.Failure(errorCodeState = ErrorCodeState.UNKNOWN_ERROR)
        }
    }
}