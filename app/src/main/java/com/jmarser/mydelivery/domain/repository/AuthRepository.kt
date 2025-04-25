package com.jmarser.mydelivery.domain.repository

import com.jmarser.mydelivery.data.modelsDto.AuthResponse
import com.jmarser.mydelivery.data.modelsDto.SignUpRequestDto
import com.jmarser.mydelivery.data.remote.repository.Resource

/**
 * Project: My Delivery
 * File: AuthRepository
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 24/04/2025
 */

interface AuthRepository {

    suspend fun tryToRegister(request: SignUpRequestDto): Resource<AuthResponse>
}