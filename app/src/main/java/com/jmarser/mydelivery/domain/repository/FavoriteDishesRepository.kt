package com.jmarser.mydelivery.domain.repository

import com.jmarser.mydelivery.domain.modelsDomain.DishItemDm
import kotlinx.coroutines.flow.Flow

/**
 * Project: My Delivery
 * File: FavoriteDishesRepository
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 23/05/2025
 */

interface FavoriteDishesRepository {

    suspend fun toggleFavorite(dish: DishItemDm)

    fun getFavorites(): Flow<List<DishItemDm>>

    fun getByIdFlow(id: String): Flow<DishItemDm?>
}