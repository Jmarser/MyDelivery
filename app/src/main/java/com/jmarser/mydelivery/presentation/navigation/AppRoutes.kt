package com.jmarser.mydelivery.presentation.navigation

import kotlinx.serialization.Serializable

/**
 * Project: My Delivery
 * File: AppRoutes
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 16/04/2025
 */

sealed class AppRoutes() {

    @Serializable
    object Welcome

    @Serializable
    object SignIn

    @Serializable
    object SignUp

    @Serializable
    object Home

}