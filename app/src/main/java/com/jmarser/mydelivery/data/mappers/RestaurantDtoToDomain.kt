package com.jmarser.mydelivery.data.mappers

import com.jmarser.mydelivery.data.modelsDto.RestaurantDto
import com.jmarser.mydelivery.domain.modelsDomain.RestaurantDm

/**
 * Project: My Delivery
 * File: RestaurantDtoToDomain
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 09/05/2025
 */

fun RestaurantDto.toDomain() = RestaurantDm(
    id = this.id,
    name = this.name,
    address = this.address,
    categoryId = this.categoryId,
    imageUrl = this.imageUrl,
    distance = this.distance
)