package com.jmarser.mydelivery.domain.repository

import com.jmarser.mydelivery.domain.modelsDomain.RestaurantDm
import kotlinx.coroutines.flow.Flow

/**
 * Project: My Delivery
 * File: FavoriteRestaurantRepository
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 16/05/2025
 */

interface FavoriteRestaurantRepository {

    suspend fun toggleFavorite(restaurant: RestaurantDm)

    fun getFavorites(): Flow<List<RestaurantDm>>
}