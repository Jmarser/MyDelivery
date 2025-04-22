package com.jmarser.mydelivery.domain.useCase

import com.jmarser.mydelivery.core.PasswordValidationResult
import com.jmarser.mydelivery.core.ValidationForm
import javax.inject.Inject

/**
 * Project: My Delivery
 * File: SignUpUseCase
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 22/04/2025
 */
 
class SignUpUseCase @Inject constructor(
    private val validationForm: ValidationForm
) {

    fun validateFieldNotEmpty(texto: String): Boolean = validationForm.validateFiledNotEmpty(texto)

    fun validateEmail(email: String): Boolean = validationForm.validateEmail(email)

    fun validatePassword(password: String): PasswordValidationResult = validationForm.validatePasswordDetail(password)

    fun validatePasswordEquals(password: String, repeatPassword: String): Boolean = validationForm.validatePasswordsEquals(password, repeatPassword)

}