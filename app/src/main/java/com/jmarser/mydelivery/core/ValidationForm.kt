package com.jmarser.mydelivery.core

import androidx.annotation.StringRes

/**
 * Project: My Delivery
 * File: ValidationForm
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 22/04/2025
 */
 
interface ValidationForm {

    fun validateFiledNotEmpty(texto: String): Boolean
    fun validateEmail(email: String): Boolean
    fun validatePassword(password: String): Boolean
    fun validatePasswordsEquals(password: String, repeatPassword: String): Boolean
    fun validHasUpperCase(password: String): Boolean
    fun validHasLowerCase(password: String): Boolean
    fun validHasNumber(password: String): Boolean
    fun validHasSpecialChar(password: String): Boolean
    fun validHasLenght(password: String): Boolean

    fun validatePasswordDetail(password: String): PasswordValidationResult
}

data class PasswordValidationResult(
    val isValid: Boolean,
    @StringRes val errorMessage: Int? = null
)