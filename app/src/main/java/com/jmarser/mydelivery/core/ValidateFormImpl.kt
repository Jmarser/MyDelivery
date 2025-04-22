package com.jmarser.mydelivery.core

import com.jmarser.mydelivery.R

/**
 * Project: My Delivery
 * File: ValidateFormImpl
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 22/04/2025
 */

class ValidateFormImpl: ValidationForm {
    override fun validateFiledNotEmpty(texto: String): Boolean {
        return texto.isNotEmpty() && texto.isNotBlank()
    }

    override fun validateEmail(email: String): Boolean = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

    override fun validatePassword(password: String): Boolean {
        val hasUpperCase = validHasUpperCase(password)
        val hasLowerCase = validHasLowerCase(password)
        val hasDigit = validHasNumber(password)
        val hasValidSpecialChar = validHasSpecialChar(password)
        val hasValidLenght = validHasLenght(password)

        return hasUpperCase && hasLowerCase && hasDigit && hasValidSpecialChar && hasValidLenght
    }

    override fun validatePasswordsEquals(password: String, repeatPassword: String): Boolean = password == repeatPassword

    override fun validHasUpperCase(password: String): Boolean = password.any { it.isUpperCase() }

    override fun validHasLowerCase(password: String): Boolean = password.any { it.isLowerCase() }

    override fun validHasNumber(password: String): Boolean = password.any { it.isDigit() }

    override fun validHasSpecialChar(password: String): Boolean {
        val regex = Regex("[!@#\$%^&*(),.?\":{}|<>]")
        return regex.containsMatchIn(password)
    }

    override fun validHasLenght(password: String): Boolean = password.length >= 6

    override fun validatePasswordDetail(password: String): PasswordValidationResult {
        val errors = mutableListOf<Int>()

        if (!validHasUpperCase(password)) errors.add(R.string.error_uppercase_required)
        if (!validHasLowerCase(password)) errors.add(R.string.error_lowercase_required)
        if (!validHasNumber(password)) errors.add(R.string.error_number_required)
        if (!validHasSpecialChar(password)) errors.add(R.string.error_special_char_required)
        if (!validHasLenght(password)) errors.add(R.string.error_min_length_required)

        return PasswordValidationResult(
            isValid = errors.isEmpty(),
            errorMessage = errors.firstOrNull()
        )

    }

}