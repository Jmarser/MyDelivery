package com.jmarser.mydelivery.data.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jmarser.mydelivery.core.Constants

@Entity(tableName = Constants.TABLE_DISHES_FAVORITES)
data class DishEntity(
    @PrimaryKey val id: String,
    val name: String,
    val imageUrl: String,
    val price: Double,
    val description: String
)
