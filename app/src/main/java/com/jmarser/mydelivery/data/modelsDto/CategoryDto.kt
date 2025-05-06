package com.jmarser.mydelivery.data.modelsDto


import com.google.gson.annotations.SerializedName

data class CategoryDto(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("imageUrl")
    val imageUrl: String?,
    @SerializedName("createdAt")
    val createdAt: String?
)