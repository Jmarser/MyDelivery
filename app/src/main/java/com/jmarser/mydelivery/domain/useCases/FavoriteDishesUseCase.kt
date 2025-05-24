package com.jmarser.mydelivery.domain.useCases

import com.jmarser.mydelivery.domain.modelsDomain.DishItemDm
import com.jmarser.mydelivery.domain.repository.FavoriteDishesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Project: My Delivery
 * File: FavoriteDishesUseCase
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 23/05/2025
 */

class FavoriteDishesUseCase @Inject constructor(
    private val repo: FavoriteDishesRepository
) {

    suspend fun toggleFavorite(dish: DishItemDm) = repo.toggleFavorite(dish)

    fun getFavorites(): Flow<List<DishItemDm>> = repo.getFavorites()

    fun getByIdFlow(id: String): Flow<DishItemDm?> = repo.getByIdFlow(id)
}