package com.jmarser.mydelivery.presentation.feature_auth.sign_in

/**
 * Project: My Delivery
 * File: SignInEffect
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 28/04/2025
 */

sealed interface SignInEffect {

    data class ShowToast(val message: String): SignInEffect
    object NavigateToHome: SignInEffect
    object ClearForm: SignInEffect
}