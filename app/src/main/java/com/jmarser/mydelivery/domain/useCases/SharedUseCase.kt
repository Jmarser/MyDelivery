package com.jmarser.mydelivery.domain.useCases

import com.jmarser.mydelivery.domain.repository.SharedRepository
import javax.inject.Inject

/**
 * Project: My Delivery
 * File: SharedUseCase
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 12/05/2025
 */

class SharedUseCase @Inject constructor(
    private val sharedRepo: SharedRepository
) {

    fun getCredentials(): Pair<String, String>?{
        val email = sharedRepo.getUserEmail()
        val password = sharedRepo.getUserPassword()

        return if (email.isNotEmpty() && password.isNotEmpty()){
            email to password
        }else {
            null
        }
    }

    fun saveAuthToken(token: String){
        sharedRepo.saveAuthToken(token)
    }

    fun saveLoginCredentials(email: String, password: String){
        sharedRepo.saveUserCredentials(email, password)
    }

    fun saveCredentials(email: String, password: String, token: String){
        sharedRepo.saveCredentials(email, password, token)
    }

    fun getUserEmail(): String = sharedRepo.getUserEmail()

    fun getUserPassword(): String = sharedRepo.getUserPassword()

    fun getAuthToken(): String = sharedRepo.getAuthToken()

    fun clearCredentials(){
        sharedRepo.clearCredentials()
    }
}