package com.jmarser.mydelivery.presentation.feature_home

import com.jmarser.mydelivery.core.ErrorCodeState
import com.jmarser.mydelivery.domain.modelsDomain.RestaurantsListDm

/**
 * Project: My Delivery
 * File: RestaurantsUiState
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 09/05/2025
 */

sealed class RestaurantsUiState {

    object Idle: RestaurantsUiState()
    object Loading: RestaurantsUiState()
    object Empty: RestaurantsUiState()
    data class Success(val data: RestaurantsListDm): RestaurantsUiState()
    data class Failure(
        val isNetwork: Boolean? = null,
        val errorCodeState: ErrorCodeState,
        val message: String? = null
    ): RestaurantsUiState()
}