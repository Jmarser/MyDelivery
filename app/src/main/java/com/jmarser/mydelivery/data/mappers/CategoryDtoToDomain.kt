package com.jmarser.mydelivery.data.mappers

import com.jmarser.mydelivery.data.modelsDto.CategoryDto
import com.jmarser.mydelivery.domain.modelsDomain.CategoryDm

/**
 * Project: My Delivery
 * File: CategoryDtoToDomain
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 06/05/2025
 */

fun CategoryDto.toDomain() = CategoryDm(
    id = this.id,
    name = this.name,
    imageUrl = this.imageUrl
)