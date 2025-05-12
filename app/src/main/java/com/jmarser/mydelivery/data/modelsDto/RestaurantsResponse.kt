package com.jmarser.mydelivery.data.modelsDto


import com.google.gson.annotations.SerializedName

data class RestaurantsResponse(
    @SerializedName("data")
    val `data`: List<RestaurantDto?>?,
    @SerializedName("status")
    val status: Int?,
    @SerializedName("message")
    val message: String?
)