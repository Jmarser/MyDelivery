package com.jmarser.mydelivery.presentation.feature_auth.sign_in

import com.jmarser.mydelivery.core.ErrorCodeState
import com.jmarser.mydelivery.domain.modelsDomain.AuthResponseDm

/**
 * Project: My Delivery
 * File: SignInUiState
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 28/04/2025
 */

sealed class SignInUiState {

    object Idle: SignInUiState()
    object Loading: SignInUiState()
    data class Success(val data: AuthResponseDm): SignInUiState()
    data class Failure(
        val isNetwork: Boolean? = null,
        val errorCodeState: ErrorCodeState,
        val message: String? = null
    ): SignInUiState()
}