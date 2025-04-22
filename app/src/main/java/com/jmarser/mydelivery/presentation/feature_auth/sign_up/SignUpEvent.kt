package com.jmarser.mydelivery.presentation.feature_auth.sign_up

/**
 * Project: My Delivery
 * File: SignUpEvent
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 22/04/2025
 */
 
sealed interface SignUpEvent {

    data class SetName(val name: String): SignUpEvent
    data class SetEmail(val email: String): SignUpEvent
    data class SetPassword(val password: String): SignUpEvent
    data class SetRepeatPassword(val repeatPassword: String): SignUpEvent
    object SignUpButtonPressed: SignUpEvent
}