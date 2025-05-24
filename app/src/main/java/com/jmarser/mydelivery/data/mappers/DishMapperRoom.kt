package com.jmarser.mydelivery.data.mappers

import com.jmarser.mydelivery.data.local.room.entities.DishEntity
import com.jmarser.mydelivery.domain.modelsDomain.DishItemDm
import com.jmarser.mydelivery.presentation.feature_restaurantDetails.DishesItem

/**
 * Project: My Delivery
 * File: DishMapperRoom
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 23/05/2025
 */

fun DishEntity.toDomain(): DishItemDm = DishItemDm(
    id = this.id,
    name = this.name,
    description = this.description,
    imageUrl = this.imageUrl,
    price = this.price,
    isFavorite = true
)

fun DishItemDm.toEntity(): DishEntity = DishEntity(
    id = this.id ?: "",
    name = this.name.orEmpty(),
    description = this.description.orEmpty(),
    imageUrl = this.imageUrl.orEmpty(),
    price = this.price ?: 0.0
)