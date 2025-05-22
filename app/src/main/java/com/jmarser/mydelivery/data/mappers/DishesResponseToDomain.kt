package com.jmarser.mydelivery.data.mappers

import com.jmarser.mydelivery.data.modelsDto.DishesResponse
import com.jmarser.mydelivery.domain.modelsDomain.DishesListDm

/**
 * Project: My Delivery
 * File: DishesResponseToDomain
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 21/05/2025
 */

fun DishesResponse.toDomain() = DishesListDm(
    data = this.data?.mapNotNull { it?.toDomain() } ?: emptyList()
)