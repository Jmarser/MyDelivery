package com.jmarser.mydelivery.presentation.feature_home

import com.jmarser.mydelivery.domain.modelsDomain.CategoryDm
import com.jmarser.mydelivery.domain.modelsDomain.RestaurantDm

/**
 * Project: My Delivery
 * File: HomeEvent
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 06/05/2025
 */

sealed interface HomeEvent {

    data class OnCategorySelected(val category: CategoryDm): HomeEvent
    data class OnRestaurantSelected(val restaurant: RestaurantDm): HomeEvent
    object ClearSearchQuery: HomeEvent
    data class ToggleFavoriteRestaurant(val restaurant: RestaurantDm): HomeEvent
    object OnRetryGetCategory: HomeEvent
    object OnRetryGetRestaurant: HomeEvent
    object OnNaviagteToBack: HomeEvent
}