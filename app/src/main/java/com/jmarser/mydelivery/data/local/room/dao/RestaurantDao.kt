package com.jmarser.mydelivery.data.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jmarser.mydelivery.core.Constants
import com.jmarser.mydelivery.data.local.room.entities.RestaurantEntity
import kotlinx.coroutines.flow.Flow

/**
 * Project: My Delivery
 * File: RestaurantDao
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 16/05/2025
 */

@Dao
interface RestaurantDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(restaurant: RestaurantEntity)

    @Delete
    suspend fun delete(restaurant: RestaurantEntity)

    @Query("SELECT * FROM ${Constants.TABLE_RESTAURANTS_FAVORITES}")
    fun getAll(): Flow<List<RestaurantEntity>>

    @Query("SELECT * FROM ${Constants.TABLE_RESTAURANTS_FAVORITES} WHERE id = :id")
    suspend fun getById(id: String): RestaurantEntity?
}