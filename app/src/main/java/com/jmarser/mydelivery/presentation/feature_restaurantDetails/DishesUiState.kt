package com.jmarser.mydelivery.presentation.feature_restaurantDetails

import com.jmarser.mydelivery.core.ErrorCodeState
import com.jmarser.mydelivery.domain.modelsDomain.DishesListDm

/**
 * Project: My Delivery
 * File: DishaesUiState
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 21/05/2025
 */

sealed class DishesUiState {

    object Idle: DishesUiState()
    object Loading: DishesUiState()
    object Empty: DishesUiState()
    data class Success(val data: DishesListDm): DishesUiState()
    data class Failure(
        val isNetwork: Boolean? = null,
        val errorCodeState: ErrorCodeState,
        val message: String? = null
    ): DishesUiState()
}