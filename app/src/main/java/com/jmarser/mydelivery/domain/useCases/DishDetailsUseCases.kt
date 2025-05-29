package com.jmarser.mydelivery.domain.useCases

import javax.inject.Inject

data class DishDetailsUseCases @Inject constructor(
    val getDishById: GetDishByIdUseCase,
    val favoriteDish: FavoriteDishesUseCase
)
