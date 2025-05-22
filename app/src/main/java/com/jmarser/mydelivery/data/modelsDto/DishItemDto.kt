package com.jmarser.mydelivery.data.modelsDto


import com.google.gson.annotations.SerializedName

data class DishItemDto(
    @SerializedName("id")
    val id: String?,
    @SerializedName("restaurantId")
    val restaurantId: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("price")
    val price: Double?,
    @SerializedName("imageUrl")
    val imageUrl: String?,
    @SerializedName("arModelUrl")
    val arModelUrl: Any?,
    @SerializedName("createdAt")
    val createdAt: String?
)