package com.jmarser.mydelivery.data.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jmarser.mydelivery.core.Constants
import com.jmarser.mydelivery.data.local.room.entities.DishEntity
import kotlinx.coroutines.flow.Flow

/**
 * Project: My Delivery
 * File: DishDao
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 23/05/2025
 */

@Dao
interface DishDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dish: DishEntity)

    @Delete
    suspend fun delete(dish: DishEntity)

    @Query("SELECT * FROM ${Constants.TABLE_DISHES_FAVORITES}")
    fun getAll(): Flow<List<DishEntity>>

    @Query("SELECT * FROM ${Constants.TABLE_DISHES_FAVORITES} WHERE id = :id")
    suspend fun getById(id: String): DishEntity?

    @Query("SELECT * FROM ${Constants.TABLE_DISHES_FAVORITES} WHERE id = :id")
    fun getByIdFlow(id: String): Flow<DishEntity?>
}