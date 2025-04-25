package com.jmarser.mydelivery.data.remote.repository

import com.jmarser.mydelivery.core.ErrorCodeState

/**
 * Project: My Delivery
 * File: Resource
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 24/04/2025
 */

sealed class Resource<out T> {

    data class Success<out T>(val value: T): Resource<T>()
    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int? = null,
        val errorMessage: String? = null,
        val errorCodeState: ErrorCodeState = ErrorCodeState.UNKNOWN_ERROR
    ): Resource<Nothing>()
    object Loading: Resource<Nothing>()
}