package com.jmarser.mydelivery.data.mappers

import com.jmarser.mydelivery.data.local.room.entities.RestaurantEntity
import com.jmarser.mydelivery.domain.modelsDomain.RestaurantDm

/**
 * Project: My Delivery
 * File: RestaurantMapperRoom
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 16/05/2025
 */

fun RestaurantEntity.toDomain() = RestaurantDm(
    id = this.id,
    name = this.name,
    address = this.address,
    categoryId = this.categoryId,
    imageUrl = this.imageUrl,
    distance = this.distance,
    isFavorite = true
)

fun RestaurantDm.toEntity() = RestaurantEntity(
    id = this.id ?: "",
    name = this.name ?: "",
    address = this.address ?: "",
    categoryId = this.categoryId ?: "",
    imageUrl = this.imageUrl ?: "",
    distance = this.distance ?: 0.0
)