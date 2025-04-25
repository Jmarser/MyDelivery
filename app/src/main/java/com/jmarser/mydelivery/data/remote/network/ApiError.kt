package com.jmarser.mydelivery.data.remote.network

import com.google.gson.JsonIOException
import com.google.gson.JsonParseException
import com.google.gson.JsonSyntaxException
import retrofit2.Response

object ApiError {

    fun <T> parserHttpError(response: Response<T>): String {
        val errorBody = try {
            response.errorBody()?.string().orEmpty()
        } catch (e: Exception) {
            null
        }


        return when (response.code()) {
            400 -> "Bad Request: ${errorBody ?: "Invalid request format."}"
            401 -> "Unauthorized: ${errorBody ?: "Authentication required"}"
            403 -> "Forbidden: ${errorBody ?: "You do not have permission to access the requested resource."}"
            404 -> "Not Found: ${errorBody ?: "The requested resource was not found."}"
            408 -> "Request Timeout: ${errorBody ?: "The request took too long to complete."}"
            500 -> "Internal Server Error: ${errorBody ?: "The server encountered an unexpected condition."}"
            502 -> "Bad Gateway: ${errorBody ?: "Invalid response from an upstream server."}"
            503 -> "Service Unavailable: ${errorBody ?: "The server is not ready to handle the request."}"
            504 -> "Gateway Timeout: ${errorBody ?: "The upstream server failed to send a request in the time allowed."}"
            else -> "Unknow Error: ${errorBody}"
        }

    }

    enum class JsonErrorType {
        SYNTAX,    // Error de sintaxis JSON.
        PARSING,   // Error al interpretar el JSON.
        STATE,     // Estado ilegal encontrado durante el procesamiento de JSON.
        IO,        // Error de entrada/salida durante el procesamiento de JSON.
        UNKNOWN    // Tipo de error no clasificado o desconocido.
    }

    class JsonError(message: String, val type: JsonErrorType) : Exception(message)

    fun parserSerializationError(exception: Exception): JsonError {
        return when (exception) {
            is JsonSyntaxException -> JsonError(
                "JSON syntax error: ${exception.message}",
                JsonErrorType.SYNTAX
            )

            is JsonParseException -> JsonError(
                "JSON parsing error: ${exception.message}",
                JsonErrorType.PARSING
            )

            is IllegalStateException -> JsonError(
                "Illegal state during JSON parsing: ${exception.message}",
                JsonErrorType.STATE
            )

            is JsonIOException -> JsonError(
                "I/O error during JSON processing: ${exception.message}",
                JsonErrorType.IO
            )

            else -> JsonError(
                "Unknown serialization error: ${exception.message}",
                JsonErrorType.UNKNOWN
            )
        }
    }
}