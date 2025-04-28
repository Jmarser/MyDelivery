package com.jmarser.mydelivery.presentation.feature_auth.sign_in

data class SignInDialogState(
    val title: String? = null,
    val message: String = "",
    val isVisible: Boolean = false
)
