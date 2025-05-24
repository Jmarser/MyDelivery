package com.jmarser.mydelivery.data.local.repository

import com.jmarser.mydelivery.data.local.room.dao.DishDao
import com.jmarser.mydelivery.data.mappers.toDomain
import com.jmarser.mydelivery.data.mappers.toEntity
import com.jmarser.mydelivery.domain.modelsDomain.DishItemDm
import com.jmarser.mydelivery.domain.repository.FavoriteDishesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Project: My Delivery
 * File: FavoriteDishesRepositoryImpl
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 23/05/2025
 */

class FavoriteDishesRepositoryImpl @Inject constructor(
    private val dao: DishDao
): FavoriteDishesRepository {
    override suspend fun toggleFavorite(dish: DishItemDm) {
        val exists = dish.id?.let { dao.getById(it) } != null

        if(exists) dao.delete(dish.toEntity()) else dao.insert(dish.toEntity())
    }

    override fun getFavorites(): Flow<List<DishItemDm>> {
        return dao.getAll().map { list -> list.map { it.toDomain() } }
    }

    override fun getByIdFlow(id: String): Flow<DishItemDm?> {
        return dao.getByIdFlow(id).map { it?.toDomain() }
    }
}