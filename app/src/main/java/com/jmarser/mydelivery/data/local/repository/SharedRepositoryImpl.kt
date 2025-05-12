package com.jmarser.mydelivery.data.local.repository

import com.jmarser.mydelivery.core.Constants
import com.jmarser.mydelivery.data.local.shared.AppPreferences
import com.jmarser.mydelivery.domain.repository.SharedRepository
import javax.inject.Inject

/**
 * Project: My Delivery
 * File: SharedRepositoryImpl
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 02/05/2025
 */

class SharedRepositoryImpl @Inject constructor(
    private val prefs: AppPreferences
): SharedRepository {
    override fun saveAuthToken(token: String) {
        prefs.save(Constants.AUTH_TOKEN, token)
    }

    override fun saveUserCredentials(email: String, password: String) {
        prefs.save(Constants.EMAIL_USER, email)
        prefs.save(Constants.PASSWORD_USER, password)
    }

    override fun saveCredentials(email: String, password: String, token: String) {
        prefs.save(Constants.EMAIL_USER, email)
        prefs.save(Constants.PASSWORD_USER, password)
        prefs.save(Constants.AUTH_TOKEN, token)
    }

    override fun getUserEmail(): String {
        return prefs.get(Constants.EMAIL_USER, "")
    }

    override fun getUserPassword(): String {
        return prefs.get(Constants.PASSWORD_USER, "")
    }

    override fun getAuthToken(): String {
        return prefs.get(Constants.AUTH_TOKEN, "")
    }

    override fun clearCredentials() {
        prefs.remove(Constants.EMAIL_USER)
        prefs.remove(Constants.PASSWORD_USER)
        prefs.remove(Constants.AUTH_TOKEN)
    }
}