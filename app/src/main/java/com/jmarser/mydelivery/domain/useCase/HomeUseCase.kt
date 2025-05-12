package com.jmarser.mydelivery.domain.useCase

import com.jmarser.mydelivery.core.ErrorCodeState
import com.jmarser.mydelivery.data.mappers.toDomain
import com.jmarser.mydelivery.data.mappers.toListRestaurantsDomain
import com.jmarser.mydelivery.data.remote.repository.Resource
import com.jmarser.mydelivery.domain.repository.HomeRepository
import com.jmarser.mydelivery.presentation.feature_home.CategoriesUiState
import com.jmarser.mydelivery.presentation.feature_home.RestaurantsUiState
import javax.inject.Inject

/**
 * Project: My Delivery
 * File: HomeUseCase
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 06/05/2025
 */

class HomeUseCase @Inject constructor(
    private val homeRepo: HomeRepository
) {

    suspend fun getAllCategories(): CategoriesUiState{
        when (val response = homeRepo.getAllCategories()){
            is Resource.Success -> {
                val data = response.value.toDomain()

                return if (data.data.isNullOrEmpty()){
                    CategoriesUiState.Empty
                }else{
                    CategoriesUiState.Success(data = data)
                }
            }
            is Resource.Failure -> return CategoriesUiState.Failure(
                isNetworkError = response.isNetworkError,
                errorCodeState = response.errorCodeState
            )
            else -> return CategoriesUiState.Failure(errorCodeState = ErrorCodeState.UNKNOWN_ERROR)
        }
    }

    suspend fun getRestaurants(lat: Double, lon: Double): RestaurantsUiState{
        when (val response = homeRepo.getRestaurants(lat, lon)){
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