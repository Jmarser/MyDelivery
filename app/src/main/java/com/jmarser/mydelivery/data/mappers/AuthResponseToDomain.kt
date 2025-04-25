package com.jmarser.mydelivery.data.mappers

import com.jmarser.mydelivery.data.modelsDto.AuthResponse
import com.jmarser.mydelivery.domain.modelsDomain.AuthResponseDm

/**
 * Project: My Delivery
 * File: AuthResponseToDomain
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 25/04/2025
 */

fun AuthResponse.toDomain() = AuthResponseDm(
    token = this.token
)