package com.jmarser.mydelivery.presentation.feature_home

import com.jmarser.mydelivery.domain.modelsDomain.CategoryDm

data class HomeUiState(
    val categoriesState: CategoriesUiState = CategoriesUiState.Idle,
    val selectedCategory: CategoryDm? = null
)
