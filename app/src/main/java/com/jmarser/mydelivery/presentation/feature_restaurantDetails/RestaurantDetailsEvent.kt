package com.jmarser.mydelivery.presentation.feature_restaurantDetails

import com.jmarser.mydelivery.domain.modelsDomain.DishItemDm
import com.jmarser.mydelivery.domain.modelsDomain.RestaurantDm

/**
 * Project: My Delivery
 * File: RestaurantDetailsEvent
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 23/05/2025
 */

sealed interface RestaurantDetailsEvent {

    data class ToggleFavoriteRestaurant(val restaurant: RestaurantDm): RestaurantDetailsEvent
    data class ToggleFavoritesDishes(val dish: DishItemDm): RestaurantDetailsEvent
    data class OnDishSelected(val dish: DishItemDm): RestaurantDetailsEvent
    object OnRetry: RestaurantDetailsEvent
    object onNavigateToBack: RestaurantDetailsEvent
}