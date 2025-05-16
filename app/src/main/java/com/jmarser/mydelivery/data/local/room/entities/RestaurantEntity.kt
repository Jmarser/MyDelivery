package com.jmarser.mydelivery.data.local.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jmarser.mydelivery.core.Constants

@Entity(tableName = Constants.TABLE_RESTAURANTS_FAVORITES)
data class RestaurantEntity(
    @PrimaryKey val id: String,
    val name: String,
    val address: String,
    @ColumnInfo(name = "category_id") val categoryId: String,
    @ColumnInfo(name = "image_url") val imageUrl: String,
    val distance: Double
)
