package com.jmarser.mydelivery.data.mappers

import com.jmarser.mydelivery.data.modelsDto.SignInRequestDto
import com.jmarser.mydelivery.domain.modelsDomain.SignInResquest

/**
 * Project: My Delivery
 * File: SignInRequestToDto
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 28/04/2025
 */

fun SignInResquest.toDto() = SignInRequestDto(
    email = this.email,
    password = this.password
)