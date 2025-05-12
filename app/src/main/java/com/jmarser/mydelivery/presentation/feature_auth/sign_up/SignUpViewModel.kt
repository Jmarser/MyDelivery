package com.jmarser.mydelivery.presentation.feature_auth.sign_up

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmarser.mydelivery.R
import com.jmarser.mydelivery.core.ErrorCodeState
import com.jmarser.mydelivery.domain.useCase.FormUseCase
import com.jmarser.mydelivery.domain.useCase.SharedUseCase
import com.jmarser.mydelivery.domain.useCase.SignUpUseCase
import com.jmarser.mydelivery.utilities.MyLog
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
 * File: SignUpViewModel
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 22/04/2025
 */

@HiltViewModel
class SignUpViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val useCase: SignUpUseCase,
    private val formUseCase: FormUseCase,
    private val sharedUseCase: SharedUseCase
): ViewModel(){

    private val _uiState = MutableStateFlow<SignUpUiState>(SignUpUiState.Idle)
    val uiState: StateFlow<SignUpUiState> = _uiState.asStateFlow()

    private val _formState = MutableStateFlow(SignUpFormState())
    val formState: StateFlow<SignUpFormState> = _formState.asStateFlow()

    private val _dialogState = MutableStateFlow(SignUpDialogState())
    val dialogState: StateFlow<SignUpDialogState> = _dialogState.asStateFlow()

    private val _effect = MutableSharedFlow<SignUpEffect>()
    val effect: SharedFlow<SignUpEffect> = _effect.asSharedFlow()

    fun onEvent(event: SignUpEvent){
        when(event){
            is SignUpEvent.SetName -> setName(event.name)
            is SignUpEvent.SetEmail -> setEmail(event.email)
            is SignUpEvent.SetPassword -> setPassword(event.password)
            is SignUpEvent.SetRepeatPassword -> setRepeatPassword(event.repeatPassword)
            SignUpEvent.SignUpButtonPressed -> tryToRegister()
            SignUpEvent.DismissDialog -> dismissDialog()
        }
    }

    private fun setName(name: String){
        val isValid = formUseCase.validateFieldNotEmpty(name)
        _formState.update {
            it.copy(
                name = name,
                isNameValid = isValid,
                nameErrorMessage = if (isValid) null else context.getString(R.string.error_name)
            )
        }

        validateSubmit()
    }

    private fun setEmail(email: String){
        val isValid = formUseCase.validateEmail(email)
        _formState.update {
            it.copy(
                email = email,
                isEmailValid = isValid,
                emailErrorMessage = if (isValid) null else context.getString(R.string.error_email)
            )
        }

        validateSubmit()
    }

    private fun setPassword(password: String){
        val result = formUseCase.validatePassword(password)
        _formState.update {
            it.copy(
                password = password,
                isPasswordValid = result.isValid,
                passwordErrorMessage = if (result.isValid) null else context.getString(result.errorMessage!!)
            )
        }

        validatePasswordMatch()
        validateSubmit()
    }

    private fun setRepeatPassword(repeatPassword: String){
        _formState.update {
            it.copy(
                repeatPassword = repeatPassword
            )
        }

        validatePasswordMatch()
        validateSubmit()
    }

    private fun validatePasswordMatch(){

        _formState.update {
            val isValid = formUseCase.validatePasswordEquals(it.password, it.repeatPassword)
            it.copy(
                isRepeatPasswordValid = isValid,
                repeatPasswordErrorMessage = if (isValid) null else context.getString(R.string.error_password_not_match)
            )
        }
    }

    private fun validateSubmit(){
        _formState.update {
            val areAllValidationNotNull = listOf(
                it.isNameValid,
                it.isEmailValid,
                it.isPasswordValid,
                it.isRepeatPasswordValid
            ).none{validation -> validation == null}

            val areAllValidationsTrue = listOf(
                it.isNameValid,
                it.isEmailValid,
                it.isPasswordValid,
                it.isRepeatPasswordValid
            ).all{validation -> validation == true}

            it.copy(
                isSubmitButtonEnabled = areAllValidationNotNull && areAllValidationsTrue
            )
        }
    }

    fun clearForm(){
        _formState.value = SignUpFormState()
    }

    private fun tryToRegister(){
        _uiState.value = SignUpUiState.Loading
        val state = _formState.value
        if (state.isSubmitButtonEnabled){
            viewModelScope.launch {
                val result = useCase.tryToRegister(state.name, state.email, state.password)

                when(result){
                    is SignUpUiState.Success -> {

                        sharedUseCase.saveCredentials(
                            email = state.email,
                            password = state.password,
                            token = result.data.token
                        )

                        _effect.emit(SignUpEffect.ShowToast(context.getString(R.string.signup_success)))
                        _effect.emit(SignUpEffect.NavigateToHome)
                    }
                    is SignUpUiState.Failure -> {
                        _dialogState.value = SignUpDialogState(
                            title = context.getString(R.string.error),
                            message = context.getString(result.errorCodeState.resourceId),
                            isVisible = true
                        )
                        _effect.emit(SignUpEffect.ClearForm)
                        _uiState.value = SignUpUiState.Idle
                    }
                    else -> {
                        _effect.emit(SignUpEffect.ShowToast(context.getString(ErrorCodeState.UNKNOWN_ERROR.resourceId)))
                        _effect.emit(SignUpEffect.ClearForm)
                        _uiState.value = SignUpUiState.Idle
                    }
                }

            }
        }else{
            MyLog.d("SignUpViewModel", "El botón esta deshabilitado")
            _uiState.value = SignUpUiState.Idle
        }
    }

    private fun dismissDialog(){
        _dialogState.update { it.copy(isVisible = false) }
    }

}