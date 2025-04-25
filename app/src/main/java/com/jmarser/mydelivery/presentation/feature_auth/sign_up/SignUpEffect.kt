package com.jmarser.mydelivery.presentation.feature_auth.sign_up

/**
 * Project: My Delivery
 * File: SignUpEffect
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 22/04/2025
 */
 
sealed interface SignUpEffect {
    data class ShowToast(val message: String): SignUpEffect
    object NavigateToHome: SignUpEffect
    object ClearForm: SignUpEffect
}