package com.jmarser.mydelivery.presentation.feature_home

import com.jmarser.mydelivery.domain.modelsDomain.CategoryDm
import com.jmarser.mydelivery.domain.modelsDomain.RestaurantDm

data class HomeUiState(
    val categoriesState: CategoriesUiState = CategoriesUiState.Idle,
    val restaurantState: RestaurantsUiState = RestaurantsUiState.Idle,
    val selectedCategory: CategoryDm? = null,
    val selectedRestaurant: RestaurantDm? = null
)
