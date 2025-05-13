package com.jmarser.mydelivery.domain.useCases

import com.jmarser.mydelivery.core.PasswordValidationResult
import com.jmarser.mydelivery.core.ValidationForm
import javax.inject.Inject

/**
 * Project: My Delivery
 * File: FormUseCase
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 12/05/2025
 */

class FormUseCase @Inject constructor(
    private val validationForm: ValidationForm
) {

    fun validateFieldNotEmpty(texto: String): Boolean = validationForm.validateFiledNotEmpty(texto)

    fun validateEmail(email: String): Boolean = validationForm.validateEmail(email)

    fun validatePassword(password: String): PasswordValidationResult = validationForm.validatePasswordDetail(password)

    fun validatePasswordEquals(password: String, repeatPassword: String): Boolean = validationForm.validatePasswordsEquals(password, repeatPassword)

}