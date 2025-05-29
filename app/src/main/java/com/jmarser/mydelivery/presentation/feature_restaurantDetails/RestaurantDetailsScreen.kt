package com.jmarser.mydelivery.presentation.feature_restaurantDetails


import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jmarser.mydelivery.R
import com.jmarser.mydelivery.core.ErrorCodeState
import com.jmarser.mydelivery.presentation.components.HeaderItem
import com.jmarser.mydelivery.presentation.components.HeaderScreenDetails
import com.jmarser.mydelivery.presentation.components.ScreenEmpty
import com.jmarser.mydelivery.presentation.components.ScreenFailure
import com.jmarser.mydelivery.presentation.components.ScreenLoading
import com.jmarser.mydelivery.presentation.components.TitleScreenDetails
import com.jmarser.mydelivery.ui.theme.MyDimens

@Composable
fun RestaurantDetailsScreen(
    modifier: Modifier = Modifier,
    restaurantId: String,
    viewModel: RestaurantDetailsViewModel = hiltViewModel(),
    onNavigateToBack: () -> Unit,
    onNavigateToDishDetails: (String) -> Unit
) {

    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val dishesState by viewModel.dishesUiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect { effect ->
            when (effect) {
                RestaurantDetailsEffect.NavigateToBack -> onNavigateToBack()
                is RestaurantDetailsEffect.NavigateToDishDetails -> {
                    onNavigateToDishDetails(effect.dish.id ?: "")
                }

                is RestaurantDetailsEffect.ShowToast -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    when (val details = uiState) {
        RestaurantDetailsUiState.Empty -> {
            ScreenEmpty(
                title = stringResource(R.string.no_data_to_display),
                subTitle = stringResource(R.string.please_try_again_later),
                onNavigateToBack = {
                    viewModel.onEvent(RestaurantDetailsEvent.onNavigateToBack)
                }
            )
        }

        is RestaurantDetailsUiState.Failure -> {
            ScreenFailure(
                isNetwork = details.isNetwork ?: false,
                message = stringResource(details.errorCodeState.resourceId),
                loading = details is RestaurantDetailsUiState.Loading,
                onRetry = {
                    viewModel.onEvent(RestaurantDetailsEvent.OnRetry)
                },
                onNavigateToBack = {
                    viewModel.onEvent(RestaurantDetailsEvent.onNavigateToBack)
                }
            )
        }

        RestaurantDetailsUiState.Loading -> {
            ScreenLoading(message = stringResource(R.string.loading_restaurant_details))
        }

        is RestaurantDetailsUiState.Success -> {

            LazyVerticalGrid(
                modifier = modifier
                    .fillMaxSize(),
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(MyDimens.dimens.paddingMedium),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item(
                    span = {
                        GridItemSpan(2)
                    }
                ) {
                    HeaderScreenDetails(
                        headerItem = HeaderItem.Restaurant(details.data),
                        onNavigateToBack = {
                            viewModel.onEvent(RestaurantDetailsEvent.onNavigateToBack)
                        },
                        onFavoriteToggle = {
                            when (it) {
                                is HeaderItem.Dish -> {
                                    // No hacemos nada por no tener plato
                                }
                                is HeaderItem.Restaurant -> {
                                    viewModel.onEvent(
                                        RestaurantDetailsEvent.ToggleFavoriteRestaurant(
                                            it.data
                                        )
                                    )

                                }
                            }
                        }
                    )
                }

                item(
                    span = {
                        GridItemSpan(2)
                    }
                ) {
                    TitleScreenDetails(
                        headerItem = HeaderItem.Restaurant(details.data),
                        onNavigateToReviews = {}
                    )
                }

                item(
                    span = {
                        GridItemSpan(2)
                    }
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = MyDimens.dimens.paddingLarge),
                        text = stringResource(id = R.string.lorem_ipsum),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                // Sección para mostrar el estado del listado de platos
                when (val dishes = dishesState) {
                    DishesUiState.Loading -> {
                        item(
                            span = {
                                GridItemSpan(2)
                            }
                        ) {
                            ScreenLoading(
                                message = stringResource(R.string.loading_restaurant_dishes)
                            )

                        }
                    }

                    DishesUiState.Empty -> {

                        item(
                            span = {
                                GridItemSpan(2)
                            }
                        ) {
                            ScreenEmpty(
                                title = stringResource(R.string.no_dishes_to_display),
                                subTitle = stringResource(R.string.please_try_again_later),
                                onNavigateToBack = {
                                    viewModel.onEvent(RestaurantDetailsEvent.onNavigateToBack)
                                }
                            )
                        }
                    }

                    is DishesUiState.Failure -> {
                        item(
                            span = {
                                GridItemSpan(2)
                            }
                        ) {

                            ScreenFailure(
                                isNetwork = dishes.isNetwork ?: false,
                                message = stringResource(dishes.errorCodeState.resourceId),
                                loading = dishes is DishesUiState.Loading,
                                onRetry = {
                                    viewModel.onEvent(RestaurantDetailsEvent.OnRetry)
                                },
                                onNavigateToBack = {
                                    viewModel.onEvent(RestaurantDetailsEvent.onNavigateToBack)
                                }
                            )

                        }

                    }

                    is DishesUiState.Success -> {

                        val dis = dishes.data.data.orEmpty().filterNotNull()

                        items(dis) { dish ->
                            DishesItem(
                                dish = dish,
                                onDishSelected = {
                                    viewModel.onEvent(RestaurantDetailsEvent.OnDishSelected(it))
                                },
                                onToggleFavoriteDish = {
                                    viewModel.onEvent(
                                        RestaurantDetailsEvent.ToggleFavoritesDishes(
                                            it
                                        )
                                    )
                                }
                            )
                        }
                    }

                    else -> {
                        item(
                            span = {
                                GridItemSpan(2)
                            }
                        ) {
                            ScreenFailure(
                                isNetwork = false,
                                message = stringResource(ErrorCodeState.UNKNOWN_ERROR.resourceId),
                                loading = dishes is DishesUiState.Loading,
                                onRetry = {
                                    viewModel.onEvent(RestaurantDetailsEvent.OnRetry)
                                },
                                onNavigateToBack = {
                                    viewModel.onEvent(RestaurantDetailsEvent.onNavigateToBack)
                                }
                            )
                        }
                    }
                }
            }
        }

        else -> {
            ScreenFailure(
                isNetwork = false,
                message = stringResource(ErrorCodeState.UNKNOWN_ERROR.resourceId),
                loading = details is RestaurantDetailsUiState.Loading,
                onRetry = {
                    viewModel.onEvent(RestaurantDetailsEvent.OnRetry)
                },
                onNavigateToBack = {
                    viewModel.onEvent(RestaurantDetailsEvent.onNavigateToBack)
                }
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun RestaurantDetailsScreenPreview() {
    RestaurantDetailsScreen(
        modifier = Modifier,
        restaurantId = "",
        onNavigateToBack = {},
        onNavigateToDishDetails = {}
    )
}
