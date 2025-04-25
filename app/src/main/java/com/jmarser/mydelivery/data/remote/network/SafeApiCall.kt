package com.jmarser.mydelivery.data.remote.network

import com.google.gson.JsonSyntaxException
import com.jmarser.mydelivery.core.ErrorCodeState
import com.jmarser.mydelivery.data.remote.repository.Resource
import com.jmarser.mydelivery.utilities.MyLog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

/**
 * Project: My Delivery
 * File: SafeApiCall
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 24/04/2025
 */

private const val TAG = "safeApiCall"

interface SafeApiCall {

    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                MyLog.d(TAG, "Iniciando la llamada a la API")
                val response = apiCall()
                if (response.isSuccessful) {
                    response.body()?.let {
                        MyLog.d(TAG, "Llamada a la Api exitosa")
                        Resource.Success(it)
                    } ?: run {
                        MyLog.e(TAG, "Llamada exitosa a la Api, pero el cuerpo esta null")
                        Resource.Failure(isNetworkError = false, errorCodeState = ErrorCodeState.SERVER_RESPONSE_NULL)
                    }
                } else {
                    val errorMessage = ApiError.parserHttpError(response)
                    MyLog.e(
                        TAG,
                        "Fallo en la llamada a la API con error HTTP: "
                    )
                    val mappedError = response.code().toErrorCodeState()

                    Resource.Failure(isNetworkError = true, errorCodeState = mappedError)
                }
            } catch (throwable: Throwable) {
                MyLog.e(
                    TAG,
                    "Fallo en la llamada a la Api con exception: ${throwable.message}"
                )
                when (throwable) {
                    is HttpException -> {
                        val errorMessage = ApiError.parserHttpError(throwable.response()!!)
                        MyLog.e(
                            TAG,
                            "Fallo en la llamada a la Api con HttpException: "
                        )
                        val response = throwable.response()
                        val errorCodeState = response?.code()?.toErrorCodeState() ?: ErrorCodeState.UNKNOWN_ERROR
                        Resource.Failure(isNetworkError = true, errorCodeState = errorCodeState)
                    }

                    is IOException -> {
                        MyLog.e(
                            TAG,
                            "Fallo en la llamada a la Api con IOException: ${throwable.message}"
                        )
                        Resource.Failure(isNetworkError = false, errorCodeState = ErrorCodeState.NO_INTERNET)
                    }

                    is ApiError.JsonError -> { // Aquí manejamos el nuevo tipo de error
                        MyLog.e(
                            TAG,
                            "Fallo de serialización del objeto: ${throwable.message}, Type: ${throwable.type}"
                        )
                        Resource.Failure(isNetworkError = false, errorCodeState = ErrorCodeState.PARSE_ERROR_USER)
                    }

                    is IllegalStateException -> {
                        MyLog.e(
                            TAG,
                            "Fallo debido a un estado no esperado en la respuesta: ${throwable.message}"
                        )
                        Resource.Failure(isNetworkError = false, errorCodeState = ErrorCodeState.PARSE_ERROR_USER)
                    }

                    is JsonSyntaxException -> {
                        MyLog.e(
                            TAG,
                            "Fallo debido a un error de sintaxis en el JSON: ${throwable.message}"
                        )
                        Resource.Failure(isNetworkError = false, errorCodeState = ErrorCodeState.PARSE_ERROR_USER)
                    }

                    else -> {
                        MyLog.e(
                            TAG,
                            "Fallo en la llamada con error desconocido: ${throwable.message}"
                        )
                        Resource.Failure(isNetworkError = true, errorCodeState = ErrorCodeState.TECHNICAL_ERROR)
                    }
                }
            }
        }
    }

    private fun Int.toErrorCodeState(): ErrorCodeState{
        return when(this){
            400 -> ErrorCodeState.TECHNICAL_ERROR
            401, 403 -> ErrorCodeState.UNAUTHORIZED_OR_FORBIDDEN
            404 -> ErrorCodeState.RESOURCE_NOT_FOUND
            408 -> ErrorCodeState.CONNECTION_TIMEOUT
            500, 502, 503, 504 -> ErrorCodeState.SERVER_NOT_RESPONDING
            in 505..599 -> ErrorCodeState.SERVER_ERROR
            else -> ErrorCodeState.UNKNOWN_ERROR
        }
    }
}