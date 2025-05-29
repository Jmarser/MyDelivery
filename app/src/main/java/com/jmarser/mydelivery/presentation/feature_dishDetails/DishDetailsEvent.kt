package com.jmarser.mydelivery.presentation.feature_dishDetails

import com.jmarser.mydelivery.domain.modelsDomain.DishItemDm

/**
 * Project: My Delivery
 * File: DishDetailsEvent
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 28/05/2025
 */

sealed interface DishDetailsEvent {

    data class ToggleFavoriteDish(val dish: DishItemDm): DishDetailsEvent
    object OnRetry: DishDetailsEvent
    object OnNavigateToBack: DishDetailsEvent

}