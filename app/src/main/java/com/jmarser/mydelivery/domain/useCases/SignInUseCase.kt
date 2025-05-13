package com.jmarser.mydelivery.domain.useCases

import com.jmarser.mydelivery.core.ErrorCodeState
import com.jmarser.mydelivery.data.mappers.toDomain
import com.jmarser.mydelivery.data.mappers.toDto
import com.jmarser.mydelivery.data.remote.repository.Resource
import com.jmarser.mydelivery.domain.modelsDomain.SignInResquest
import com.jmarser.mydelivery.domain.repository.AuthRepository
import com.jmarser.mydelivery.presentation.feature_auth.sign_in.SignInUiState
import javax.inject.Inject

/**
 * Project: My Delivery
 * File: SignInUseCase
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 28/04/2025
 */

class SignInUseCase @Inject constructor(
    private val authRepo: AuthRepository
) {


    suspend fun tryToLogin(email: String, password: String): SignInUiState {
        val request = SignInResquest(
            email = email,
            password = password
        )

        when (val response = authRepo.tryToLogin(request.toDto())) {
            is Resource.Success -> {
                return SignInUiState.Success(data = response.value.toDomain())
            }

            is Resource.Failure -> {
                return SignInUiState.Failure(
                    isNetwork = response.isNetworkError,
                    errorCodeState = response.errorCodeState
                )
            }

            else -> return SignInUiState.Failure(errorCodeState = ErrorCodeState.UNKNOWN_ERROR)
        }
    }
}