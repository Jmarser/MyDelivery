package com.jmarser.mydelivery.presentation.feature_auth.sign_in

data class SignInFormState(
    val email: String = "",
    val password: String = "",

    val emailErrorMessage: String? = null,
    val passwordErrorMessage: String? = null,

    val isEmailValid: Boolean? = null,
    val isPasswordValid: Boolean? = null,

    val isSubmitButtonEnabled: Boolean = false
)
