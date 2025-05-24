package com.jmarser.mydelivery.di

import android.content.Context
import androidx.room.Room
import com.jmarser.mydelivery.BuildConfig
import com.jmarser.mydelivery.core.Constants
import com.jmarser.mydelivery.data.local.room.dao.DishDao
import com.jmarser.mydelivery.data.local.room.dao.RestaurantDao
import com.jmarser.mydelivery.data.local.room.database.AppDatabase
import com.jmarser.mydelivery.data.local.room.migrations.MIGRATION_1_2
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomDatabaseModule {

    @Provides
    fun provideDao(database: AppDatabase): RestaurantDao = database.restaurantDao()

    @Provides
    fun provideDishDao(database: AppDatabase): DishDao = database.dishDao()

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {

        val builder = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            Constants.DATABASE_NAME
        )

        if(BuildConfig.DESTRUCTIVE_MIGRATION_ENABLED){
            builder.fallbackToDestructiveMigration(true)
        }else{
            builder.addMigrations(MIGRATION_1_2)
        }

        return builder.build()
    }
}