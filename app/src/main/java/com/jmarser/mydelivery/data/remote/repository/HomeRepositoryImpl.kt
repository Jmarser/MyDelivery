package com.jmarser.mydelivery.data.remote.repository

import com.jmarser.mydelivery.data.remote.network.ApiService
import com.jmarser.mydelivery.data.remote.network.SafeApiCall
import com.jmarser.mydelivery.domain.repository.HomeRepository
import javax.inject.Inject

/**
 * Project: My Delivery
 * File: HomeRepositoryImpl
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 06/05/2025
 */

class HomeRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): HomeRepository, SafeApiCall {

    override suspend fun getAllCategories() = safeApiCall {
        apiService.getAllCategories()
    }

    override suspend fun getRestaurants(lat: Double, lon: Double) = safeApiCall {
        apiService.getRestaurants(lat, lon)
    }
}