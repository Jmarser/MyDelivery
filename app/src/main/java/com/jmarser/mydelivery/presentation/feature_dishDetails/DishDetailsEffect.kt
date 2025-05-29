package com.jmarser.mydelivery.presentation.feature_dishDetails

/**
 * Project: My Delivery
 * File: DishDetailsEffect
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 28/05/2025
 */

sealed interface DishDetailsEffect {

    object NavigateToBack: DishDetailsEffect
    data class ShowToast(val message: String): DishDetailsEffect
}