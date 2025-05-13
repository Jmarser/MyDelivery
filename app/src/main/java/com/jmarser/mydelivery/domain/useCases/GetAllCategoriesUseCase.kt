package com.jmarser.mydelivery.domain.useCases

import com.jmarser.mydelivery.core.ErrorCodeState
import com.jmarser.mydelivery.data.mappers.toDomain
import com.jmarser.mydelivery.data.remote.repository.Resource
import com.jmarser.mydelivery.domain.repository.HomeRepository
import com.jmarser.mydelivery.presentation.feature_home.CategoriesUiState
import javax.inject.Inject

/**
 * Project: My Delivery
 * File: GetAllCategoriesUseCase
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 13/05/2025
 */

class GetAllCategoriesUseCase @Inject constructor(
    private val repo: HomeRepository
) {

    suspend operator fun invoke(): CategoriesUiState{
        when (val response = repo.getAllCategories()){
            is Resource.Success -> {
                val data = response.value.toDomain()

                return if (data.data.isNullOrEmpty()){
                    CategoriesUiState.Empty
                }else{
                    CategoriesUiState.Success(data = data)
                }
            }
            is Resource.Failure -> return CategoriesUiState.Failure(
                isNetworkError = response.isNetworkError,
                errorCodeState = response.errorCodeState
            )
            else -> return CategoriesUiState.Failure(errorCodeState = ErrorCodeState.UNKNOWN_ERROR)
        }
    }
}