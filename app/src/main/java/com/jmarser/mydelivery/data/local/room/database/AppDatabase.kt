package com.jmarser.mydelivery.data.local.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jmarser.mydelivery.data.local.room.dao.RestaurantDao
import com.jmarser.mydelivery.data.local.room.entities.RestaurantEntity

/**
 * Project: My Delivery
 * File: AppDatabase
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 16/05/2025
 */

@Database(entities = [RestaurantEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun restaurantDao(): RestaurantDao
}