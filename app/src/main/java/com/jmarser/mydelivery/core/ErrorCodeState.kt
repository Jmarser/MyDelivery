package com.jmarser.mydelivery.core

import com.jmarser.mydelivery.R

/**
 * Project: My Delivery
 * File: ErrorCodeState
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 25/04/2025
 */

enum class ErrorCodeState(val resourceId: Int) {
    USER_UNKNOWN(R.string.user_unknowm),
    USER_EMPTY(R.string.user_empty),
    IDENTIFICATION_ERROR(R.string.identification_error),
    NETWORK_ERROR(R.string.network_error),
    UNKNOWN_ERROR(R.string.unknown_error),
    JSON_SERIALIZATION_ERROR(R.string.serialization_error),
    NEWS_ERROR(R.string.news_error),
    NEWS_EMPTY(R.string.news_empty),
    LOGIN_ERROR(R.string.login_error),
    REGISTER_ERROR(R.string.register_error),
    USER_ALREADY_EXISTS(R.string.user_already_exists),
    USER_NOT_FOUND(R.string.user_not_found),
    USER_BLOCKED(R.string.user_blocked),
    USER_NOT_ACTIVE(R.string.user_not_active),
    RESET_PASSWORD_ERROR(R.string.reset_password_error),
    USER_REGISTRATION_ERROR(R.string.user_registration_error),

    TECHNICAL_ERROR(R.string.technical_error), // Error técnico inesperado. HTTP 400
    UNAUTHORIZED_OR_FORBIDDEN(R.string.error_unauthorized_or_forbidden), // HTTP 401, 403
    RESOURCE_NOT_FOUND(R.string.error_resource_not_found), // HTTP 404
    CONNECTION_TIMEOUT(R.string.connection_timeout), // Tiempo de espera agotado. // HTTP 408
    SERVER_ERROR(R.string.error_server_error), // HTTP 500
    SERVER_NOT_RESPONDING(R.string.server_not_responding), // El servidor no responde. HTTP 502, 503, 504

    PARSE_ERROR_USER(R.string.parse_error_user), // Ha ocurrido un problema inesperado al procesar los datos.
    SERVER_RESPONSE_NULL(R.string.server_response_null),
    NO_INTERNET(R.string.no_internet_connection), // El dispositivo no tiene conexión a internet.
}