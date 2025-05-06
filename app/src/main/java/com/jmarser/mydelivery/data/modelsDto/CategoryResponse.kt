package com.jmarser.mydelivery.data.modelsDto


import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName("data")
    val `data`: List<CategoryDto?>?
)