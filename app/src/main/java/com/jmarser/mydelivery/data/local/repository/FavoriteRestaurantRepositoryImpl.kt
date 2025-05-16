package com.jmarser.mydelivery.data.local.repository

import com.jmarser.mydelivery.data.local.room.dao.RestaurantDao
import com.jmarser.mydelivery.data.mappers.toDomain
import com.jmarser.mydelivery.data.mappers.toEntity
import com.jmarser.mydelivery.domain.modelsDomain.RestaurantDm
import com.jmarser.mydelivery.domain.repository.FavoriteRestaurantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Project: My Delivery
 * File: FavoriteRestaurantRepositoryImpl
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 16/05/2025
 */

class FavoriteRestaurantRepositoryImpl @Inject constructor(
    private val dao: RestaurantDao
): FavoriteRestaurantRepository {

    override suspend fun toggleFavorite(restaurant: RestaurantDm) {
        val exists = restaurant.id?.let { dao.getById(it) } != null

        if (exists) dao.delete(restaurant.toEntity()) else dao.insert(restaurant.toEntity())
    }

    override fun getFavorites(): Flow<List<RestaurantDm>> {
        return dao.getAll().map { list -> list.map { it.toDomain() } }
    }
}