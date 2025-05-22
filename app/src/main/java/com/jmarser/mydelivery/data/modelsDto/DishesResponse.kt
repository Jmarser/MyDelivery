package com.jmarser.mydelivery.data.modelsDto


import com.google.gson.annotations.SerializedName

data class DishesResponse(
    @SerializedName("foodItems")
    val data: List<DishItemDto?>?
)