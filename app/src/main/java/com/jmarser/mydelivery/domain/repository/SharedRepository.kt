package com.jmarser.mydelivery.domain.repository

/**
 * Project: My Delivery
 * File: SharedRepository
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 02/05/2025
 */

interface SharedRepository {

    fun saveAuthToken(token: String)

    fun saveUserCredentials(email: String, password: String)

    fun getUserEmail(): String

    fun getUserPassword(): String

    fun getAuthToken(): String
}