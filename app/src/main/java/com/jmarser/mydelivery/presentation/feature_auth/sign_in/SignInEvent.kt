package com.jmarser.mydelivery.presentation.feature_auth.sign_in

/**
 * Project: My Delivery
 * File: SignInEvent
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 28/04/2025
 */

sealed interface SignInEvent {

    data class SetEmail(val email: String): SignInEvent
    data class SetPassword(val password: String): SignInEvent
    object SignInButtonPressed: SignInEvent
    object DismissDialog: SignInEvent
}