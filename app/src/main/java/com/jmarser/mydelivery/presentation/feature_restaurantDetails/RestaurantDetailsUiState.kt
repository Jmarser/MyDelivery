package com.jmarser.mydelivery.presentation.feature_restaurantDetails

import com.jmarser.mydelivery.core.ErrorCodeState
import com.jmarser.mydelivery.domain.modelsDomain.RestaurantDm

/**
 * Project: My Delivery
 * File: RestaurantDetailsUiState
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 20/05/2025
 */

sealed class RestaurantDetailsUiState {

    object Idle: RestaurantDetailsUiState()
    object Loading: RestaurantDetailsUiState()
    object Empty: RestaurantDetailsUiState()
    data class Success(val data: RestaurantDm): RestaurantDetailsUiState()
    data class Failure(
        val isNetwork: Boolean? = null,
        val errorCodeState: ErrorCodeState,
        val message: String? = null
    ): RestaurantDetailsUiState()
}