package com.jmarser.mydelivery.presentation.feature_dishDetails

import com.jmarser.mydelivery.core.ErrorCodeState
import com.jmarser.mydelivery.domain.modelsDomain.DishItemDm

/**
 * Project: My Delivery
 * File: DishDetailsUiState
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 28/05/2025
 */

sealed class DishDetailsUiState {

    object Idle: DishDetailsUiState()
    object Loading: DishDetailsUiState()
    object Empty: DishDetailsUiState()
    data class Success(val data: DishItemDm): DishDetailsUiState()
    data class Failure(
        val isNetwork: Boolean? = null,
        val errorCodeState: ErrorCodeState,
        val message: String? = null
    ): DishDetailsUiState()
}