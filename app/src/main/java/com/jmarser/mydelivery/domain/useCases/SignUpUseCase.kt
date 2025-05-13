package com.jmarser.mydelivery.domain.useCases

import com.jmarser.mydelivery.core.ErrorCodeState
import com.jmarser.mydelivery.data.mappers.toDomain
import com.jmarser.mydelivery.data.mappers.toDto
import com.jmarser.mydelivery.data.remote.repository.Resource
import com.jmarser.mydelivery.domain.modelsDomain.SignUpRequest
import com.jmarser.mydelivery.domain.repository.AuthRepository
import com.jmarser.mydelivery.presentation.feature_auth.sign_up.SignUpUiState
import javax.inject.Inject

/**
 * Project: My Delivery
 * File: SignUpUseCase
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 22/04/2025
 */
 
class SignUpUseCase @Inject constructor(
    private val authRepo: AuthRepository
) {

    suspend fun tryToRegister(name: String, email: String, password: String): SignUpUiState{

        val request = SignUpRequest(
            name = name,
            email = email,
            password = password
        )

        when(val response = authRepo.tryToRegister(request.toDto())){
            is Resource.Success -> {
                return SignUpUiState.Success(data = response.value.toDomain())
            }
            is Resource.Failure -> return SignUpUiState.Failure(isNetwork = response.isNetworkError, errorCodeState = response.errorCodeState)
            else -> return SignUpUiState.Failure(errorCodeState = ErrorCodeState.UNKNOWN_ERROR)
        }
    }

}