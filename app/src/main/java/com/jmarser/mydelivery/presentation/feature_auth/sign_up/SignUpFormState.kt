package com.jmarser.mydelivery.presentation.feature_auth.sign_up

data class SignUpFormState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val repeatPassword: String = "",

    val nameErrorMessage: String? = null,
    val emailErrorMessage: String? = null,
    val passwordErrorMessage: String? = null,
    val repeatPasswordErrorMessage: String? = null,

    val isNameValid: Boolean? = null,
    val isEmailValid: Boolean? = null,
    val isPasswordValid: Boolean? = null,
    val isRepeatPasswordValid: Boolean? = null,

    val isSubmitButtonEnabled: Boolean = false
)
