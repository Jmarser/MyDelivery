package com.jmarser.mydelivery.data.mappers

import com.jmarser.mydelivery.data.modelsDto.RestaurantsResponse
import com.jmarser.mydelivery.domain.modelsDomain.ErrorResponseDm
import com.jmarser.mydelivery.domain.modelsDomain.RestaurantsListDm

/**
 * Project: My Delivery
 * File: RestaurantsResponseToDomain
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 09/05/2025
 */

fun RestaurantsResponse.toListRestaurantsDomain() = RestaurantsListDm(
    data = this.data?.mapNotNull { it?.toDomain() } ?: emptyList()
)

fun RestaurantsResponse.toErrorResponse() = ErrorResponseDm(
    status = this.status,
    message = this.message
)