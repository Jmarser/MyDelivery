package com.jmarser.mydelivery.presentation.feature_auth.welcome

/**
 * Project: My Delivery
 * File: WelcomeEffect
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 02/05/2025
 */

sealed interface WelcomeEffect {

    object NavigateToHome: WelcomeEffect
    object NavigateToSignIn: WelcomeEffect
    data class ShowToast(val message: String): WelcomeEffect
}