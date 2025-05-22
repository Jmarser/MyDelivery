package com.jmarser.mydelivery.data.mappers

import com.jmarser.mydelivery.data.modelsDto.DishItemDto
import com.jmarser.mydelivery.domain.modelsDomain.DishItemDm

/**
 * Project: My Delivery
 * File: DishItemDtoToDomain
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 21/05/2025
 */
 

fun DishItemDto.toDomain() = DishItemDm(
    id = this.id,
    name = this.name,
    description = this.description,
    price = this.price,
    imageUrl = this.imageUrl
)