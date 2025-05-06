package com.jmarser.mydelivery.presentation.feature_home

import com.jmarser.mydelivery.core.ErrorCodeState
import com.jmarser.mydelivery.domain.modelsDomain.CategoryResponseDm

/**
 * Project: My Delivery
 * File: CategoriesUiState
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 06/05/2025
 */

sealed class CategoriesUiState {

    object Idle: CategoriesUiState()
    object Loading: CategoriesUiState()
    object Empty: CategoriesUiState()
    data class Success(val data: CategoryResponseDm?): CategoriesUiState()
    data class Failure(
        val isNetworkError: Boolean? = null,
        val errorCodeState: ErrorCodeState,
        val message: String? = null
    ): CategoriesUiState()
}