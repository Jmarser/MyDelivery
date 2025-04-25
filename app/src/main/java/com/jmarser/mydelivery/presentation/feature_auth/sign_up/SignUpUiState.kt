package com.jmarser.mydelivery.presentation.feature_auth.sign_up

import com.jmarser.mydelivery.core.ErrorCodeState
import com.jmarser.mydelivery.domain.modelsDomain.AuthResponseDm

/**
 * Project: My Delivery
 * File: SignUpUiState
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 22/04/2025
 */
 
sealed class SignUpUiState {

    object Idle: SignUpUiState()
    object Loading: SignUpUiState()
    data class Success(val data: AuthResponseDm): SignUpUiState()
    data class Failure(
        val isNetwork: Boolean? = null,
        val errorCodeState: ErrorCodeState,
        val message: String? = null
    ): SignUpUiState()
}