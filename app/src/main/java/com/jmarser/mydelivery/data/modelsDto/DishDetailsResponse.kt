package com.jmarser.mydelivery.data.modelsDto


import com.google.gson.annotations.SerializedName

data class DishDetailsResponse(
    @SerializedName("data")
    val `data`: DishItemDto?
)