package com.jmarser.mydelivery.data.mappers

import com.jmarser.mydelivery.data.modelsDto.CategoryResponse
import com.jmarser.mydelivery.domain.modelsDomain.CategoryResponseDm

/**
 * Project: My Delivery
 * File: CategoryResponseToDomain
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 06/05/2025
 */
 

fun CategoryResponse.toDomain() = CategoryResponseDm(
    data = this.data?.mapNotNull { it?.toDomain() } ?: emptyList()
)