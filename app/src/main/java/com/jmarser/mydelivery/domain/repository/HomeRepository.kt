package com.jmarser.mydelivery.domain.repository

import com.jmarser.mydelivery.data.modelsDto.CategoryResponse
import com.jmarser.mydelivery.data.modelsDto.DishesResponse
import com.jmarser.mydelivery.data.modelsDto.RestaurantDetailsResponse
import com.jmarser.mydelivery.data.modelsDto.RestaurantsResponse
import com.jmarser.mydelivery.data.remote.repository.Resource

/**
 * Project: My Delivery
 * File: HomeRepository
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 06/05/2025
 */

interface HomeRepository {

    suspend fun getAllCategories(): Resource<CategoryResponse>

    suspend fun getRestaurants(lat: Double, lon: Double): Resource<RestaurantsResponse>

    suspend fun getRestaurantsByCategory(lat: Double, lon: Double, category: String): Resource<RestaurantsResponse>

    suspend fun getRestaurantById(restaurantId: String): Resource<RestaurantDetailsResponse>

    suspend fun getDishesByRestaurant(restaurantId: String): Resource<DishesResponse>
}