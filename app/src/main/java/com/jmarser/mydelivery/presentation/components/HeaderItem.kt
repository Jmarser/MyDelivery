package com.jmarser.mydelivery.presentation.components

import com.jmarser.mydelivery.domain.modelsDomain.DishItemDm
import com.jmarser.mydelivery.domain.modelsDomain.RestaurantDm

/**
 * Project: My Delivery
 * File: HeaderItem
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 28/05/2025
 */

sealed class HeaderItem {

    data class Restaurant(val data: RestaurantDm): HeaderItem()
    data class Dish(val data: DishItemDm): HeaderItem()

    val imageUrl: String
        get() = when(this){
            is Dish -> data.imageUrl ?: ""
            is Restaurant -> data.imageUrl ?: ""
        }

    val name: String
        get() = when(this){
            is Dish -> data.name ?: ""
            is Restaurant -> data.name ?: ""
        }

    val isFavorite: Boolean
        get() = when(this){
            is Dish -> data.isFavorite
            is Restaurant -> data.isFavorite
        }
}