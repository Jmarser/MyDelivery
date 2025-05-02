package com.jmarser.mydelivery.presentation.feature_auth.welcome

/**
 * Project: My Delivery
 * File: WelcomeEvent
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 02/05/2025
 */

sealed interface WelcomeEvent {

    object SignInButtonPressed: WelcomeEvent
}