package com.jmarser.mydelivery.domain.useCases

import com.jmarser.mydelivery.domain.modelsDomain.RestaurantDm
import com.jmarser.mydelivery.domain.repository.FavoriteRestaurantRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Project: My Delivery
 * File: FavoritesRestaurantsUseCase
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 16/05/2025
 */

class FavoritesRestaurantsUseCase @Inject constructor(
    private val repo: FavoriteRestaurantRepository
) {

    suspend fun toggleFavorite(restaurant: RestaurantDm) = repo.toggleFavorite(restaurant)

    fun getFavorites(): Flow<List<RestaurantDm>> = repo.getFavorites()

    fun getByIdFlow(id: String): Flow<RestaurantDm?> = repo.getByIdFlow(id)
}