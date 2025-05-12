package com.jmarser.mydelivery.presentation.feature_home

import com.jmarser.mydelivery.domain.modelsDomain.CategoryDm
import com.jmarser.mydelivery.domain.modelsDomain.RestaurantDm

/**
 * Project: My Delivery
 * File: HomeEffect
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 06/05/2025
 */

sealed interface HomeEffect {

    data class CategorySelected(val category: CategoryDm): HomeEffect
    data class RestaurantSelected(val restaurant: RestaurantDm): HomeEffect
}