package com.jmarser.mydelivery.presentation.feature_auth.sign_up

/**
 * Project: My Delivery
 * File: SignUpUiState
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 22/04/2025
 */
 
sealed class SignUpUiState {

    object Idle: SignUpUiState()
    object Loading: SignUpUiState()
    data class Success(val successMessage: String?): SignUpUiState()
    data class Failure(val failureMessage: String): SignUpUiState()
}