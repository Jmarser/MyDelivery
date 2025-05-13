package com.jmarser.mydelivery.domain.useCases

import com.jmarser.mydelivery.core.ErrorCodeState
import com.jmarser.mydelivery.data.mappers.toDomain
import com.jmarser.mydelivery.data.mappers.toListRestaurantsDomain
import com.jmarser.mydelivery.data.remote.repository.Resource
import com.jmarser.mydelivery.domain.repository.HomeRepository
import com.jmarser.mydelivery.presentation.feature_home.CategoriesUiState
import com.jmarser.mydelivery.presentation.feature_home.RestaurantsUiState
import javax.inject.Inject

/**
 * Project: My Delivery
 * File: HomeUseCase
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 06/05/2025
 */

data class HomeUseCases @Inject constructor(
    val getAllCategories: GetAllCategoriesUseCase,
    val getRestaurants: GetRestaurantsUseCase,
    val getRestaurantsByCategory: GetRestaurantsByCategoryUseCase
)