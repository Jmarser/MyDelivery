package com.jmarser.mydelivery.di

import com.jmarser.mydelivery.data.local.repository.FavoriteRestaurantRepositoryImpl
import com.jmarser.mydelivery.data.local.room.dao.RestaurantDao
import com.jmarser.mydelivery.domain.repository.FavoriteRestaurantRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FavoritesRestaurantsModule {

    @Provides
    fun provideFavoriteRestaurantRepository(
        dao: RestaurantDao
    ): FavoriteRestaurantRepository = FavoriteRestaurantRepositoryImpl(dao)
}