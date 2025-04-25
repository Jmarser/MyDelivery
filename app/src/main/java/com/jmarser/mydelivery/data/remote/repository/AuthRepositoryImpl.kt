package com.jmarser.mydelivery.data.remote.repository

import com.jmarser.mydelivery.data.modelsDto.SignUpRequestDto
import com.jmarser.mydelivery.data.remote.network.ApiService
import com.jmarser.mydelivery.data.remote.network.SafeApiCall
import com.jmarser.mydelivery.domain.repository.AuthRepository
import javax.inject.Inject

/**
 * Project: My Delivery
 * File: AuthRepository
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 24/04/2025
 */

class AuthRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): AuthRepository, SafeApiCall {


    override suspend fun tryToRegister(request: SignUpRequestDto) = safeApiCall {
        apiService.signUp(request)
    }


}