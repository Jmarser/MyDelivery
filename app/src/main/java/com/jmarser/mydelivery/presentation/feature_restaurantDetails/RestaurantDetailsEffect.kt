package com.jmarser.mydelivery.presentation.feature_restaurantDetails

import com.jmarser.mydelivery.domain.modelsDomain.DishItemDm

/**
 * Project: My Delivery
 * File: RestaurantDetailsEffect
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 23/05/2025
 */

sealed interface RestaurantDetailsEffect {

    data class NavigateToDishDetails(val dish: DishItemDm): RestaurantDetailsEffect
    object NavigateToBack: RestaurantDetailsEffect
    data class ShowToast(val message: String): RestaurantDetailsEffect
}