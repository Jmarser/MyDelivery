package com.jmarser.mydelivery.presentation.feature_auth.welcome

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmarser.mydelivery.R
import com.jmarser.mydelivery.domain.useCase.SharedUseCase
import com.jmarser.mydelivery.domain.useCase.SignInUseCase
import com.jmarser.mydelivery.presentation.feature_auth.sign_in.SignInUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Project: My Delivery
 * File: WelcomeViewModel
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 02/05/2025
 */

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val useCase: SignInUseCase,
    private val sharedUseCase: SharedUseCase,
): ViewModel(){

    private val _effect = MutableSharedFlow<WelcomeEffect>()
    val effect: SharedFlow<WelcomeEffect> = _effect.asSharedFlow()

    fun onEvent(event: WelcomeEvent){
        when(event){
            WelcomeEvent.SignInButtonPressed -> validateCredentials()
        }
    }

    private fun validateCredentials(){
        sharedUseCase.getCredentials()?.let { (email, password) ->
            viewModelScope.launch {
                if (email.isNotEmpty() && password.isNotEmpty()){
                    val result = useCase.tryToLogin(email, password)

                    when(result){
                        is SignInUiState.Success -> {

                            sharedUseCase.saveAuthToken(token = result.data.token)

                            _effect.emit(WelcomeEffect.ShowToast(context.getString(R.string.signin_success)))
                            _effect.emit(WelcomeEffect.NavigateToHome)
                        }
                        else -> {
                            sharedUseCase.clearCredentials()
                            _effect.emit(WelcomeEffect.NavigateToSignIn)
                        }
                    }

                }else{
                    _effect.emit(WelcomeEffect.NavigateToSignIn)
                }
            }
        } ?: run {
            viewModelScope.launch {
                _effect.emit(WelcomeEffect.NavigateToSignIn)
            }
        }
    }
}