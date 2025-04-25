package com.jmarser.mydelivery.data.mappers

import com.jmarser.mydelivery.data.modelsDto.SignUpRequestDto
import com.jmarser.mydelivery.domain.modelsDomain.SignUpRequest

/**
 * Project: My Delivery
 * File: SignUpRequestToDto
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 25/04/2025
 */

fun SignUpRequest.toDto() = SignUpRequestDto(
    name = this.name,
    email = this.email,
    password = this.password
)