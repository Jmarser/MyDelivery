package com.jmarser.mydelivery.data.modelsDto


import com.google.gson.annotations.SerializedName

data class RestaurantDetailsResponse(
    @SerializedName("data")
    val `data`: RestaurantDto?
)