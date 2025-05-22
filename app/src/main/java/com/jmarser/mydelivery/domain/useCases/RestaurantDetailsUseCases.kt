package com.jmarser.mydelivery.domain.useCases

import javax.inject.Inject

data class RestaurantDetailsUseCases @Inject constructor(
    val getRestaurantDetailsUseCase: GetRestaurantByIdUseCase,
    val getDishesByRestaurantUseCase: GetDishesByRestaurantUseCase,
    val favoriteRestaurant: FavoritesRestaurantsUseCase
)
