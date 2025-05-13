package com.jmarser.mydelivery.presentation.feature_auth.sign_in

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmarser.mydelivery.R
import com.jmarser.mydelivery.core.ErrorCodeState
import com.jmarser.mydelivery.domain.useCases.FormUseCase
import com.jmarser.mydelivery.domain.useCases.SharedUseCase
import com.jmarser.mydelivery.domain.useCases.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Project: My Delivery
 * File: SignInViewModel
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 28/04/2025
 */


@HiltViewModel
class SignInViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val useCase: SignInUseCase,
    private val formUseCase: FormUseCase,
    private val sharedUseCase: SharedUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<SignInUiState>(SignInUiState.Idle)
    val uiState: StateFlow<SignInUiState> = _uiState.asStateFlow()

    private val _formState = MutableStateFlow(SignInFormState())
    val formState: StateFlow<SignInFormState> = _formState.asStateFlow()

    private val _dialogState = MutableStateFlow(SignInDialogState())
    val dialogState: StateFlow<SignInDialogState> = _dialogState.asStateFlow()

    private val _effect = MutableSharedFlow<SignInEffect>()
    val effect: SharedFlow<SignInEffect> = _effect.asSharedFlow()

    init {
        validateCredentials()
    }

    fun onEvent(event: SignInEvent) {
        when (event) {
            is SignInEvent.SetEmail -> setEmail(event.email)
            is SignInEvent.SetPassword -> setPassword(event.password)
            SignInEvent.SignInButtonPressed -> tryToLogin()
            SignInEvent.DismissDialog -> dismissDialog()
        }
    }

    private fun setEmail(value: String) {
        val isValid = formUseCase.validateEmail(value)
        _formState.update {
            it.copy(
                email = value,
                isEmailValid = isValid,
                emailErrorMessage = if (isValid) null else context.getString(R.string.error_email)
            )
        }

        validateSubmit()
    }

    private fun setPassword(value: String) {
        val result = formUseCase.validatePassword(value)

        _formState.update {
            it.copy(
                password = value,
                isPasswordValid = result.isValid,
                passwordErrorMessage = if (result.isValid) null else context.getString(result.errorMessage!!)
            )
        }

        validateSubmit()
    }

    private fun validateSubmit() {
        _formState.update {
            val areAllValidationsNotNull = listOf(
                it.isEmailValid,
                it.isPasswordValid
            ).none { validation -> validation == null }

            val areAllValidationsTrue = listOf(
                it.isEmailValid,
                it.isPasswordValid
            ).all { validation -> validation == true }

            it.copy(
                isSubmitButtonEnabled = areAllValidationsNotNull && areAllValidationsTrue
            )
        }
    }

    fun clearForm() {
        _formState.value = SignInFormState()
    }

    private fun dismissDialog() {
        _dialogState.update { it.copy(isVisible = false) }
    }

    private fun validateCredentials() {
        sharedUseCase.getCredentials()?.let { (email, password) ->
            login(email, password, false)
        }
    }

    private fun tryToLogin() {
        _uiState.value = SignInUiState.Loading
        val state = _formState.value

        if (state.isSubmitButtonEnabled) {
            login(state.email, state.password, true)
        }
    }

    private fun login(email: String, password: String, clearForm: Boolean) {
        viewModelScope.launch {
            val result = useCase.tryToLogin(email, password)

            when (result) {
                is SignInUiState.Success -> {
                    sharedUseCase.saveCredentials(
                        email = email,
                        password = password,
                        token = result.data.token
                    )

                    _effect.emit(SignInEffect.ShowToast(context.getString(R.string.signin_success)))
                    _effect.emit(SignInEffect.NavigateToHome)
                }

                is SignInUiState.Failure -> {

                    sharedUseCase.clearCredentials()

                    _dialogState.value = SignInDialogState(
                        title = context.getString(R.string.error),
                        message = context.getString(result.errorCodeState.resourceId),
                        isVisible = true
                    )
                    if (clearForm) _effect.emit(SignInEffect.ClearForm)
                    _uiState.value = SignInUiState.Idle
                }

                else -> {
                    if (clearForm) _effect.emit(SignInEffect.ClearForm)
                    _effect.emit(SignInEffect.ShowToast(context.getString(ErrorCodeState.UNKNOWN_ERROR.resourceId)))
                    _uiState.value = SignInUiState.Idle
                }
            }
        }
    }
}