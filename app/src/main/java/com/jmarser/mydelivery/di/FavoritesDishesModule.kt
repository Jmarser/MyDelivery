package com.jmarser.mydelivery.di

import com.jmarser.mydelivery.data.local.repository.FavoriteDishesRepositoryImpl
import com.jmarser.mydelivery.data.local.room.dao.DishDao
import com.jmarser.mydelivery.domain.repository.FavoriteDishesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FavoritesDishesModule {

    @Provides
    fun provideFavoriteDishesRepository(
        dao: DishDao
    ): FavoriteDishesRepository = FavoriteDishesRepositoryImpl(dao)
}