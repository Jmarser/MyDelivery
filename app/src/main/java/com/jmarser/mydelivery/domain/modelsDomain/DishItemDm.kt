package com.jmarser.mydelivery.domain.modelsDomain

data class DishItemDm(
    val id: String?,
    val name: String?,
    val description: String?,
    val price: Double?,
    val imageUrl: String?,
    val isFavorite: Boolean = false
)
