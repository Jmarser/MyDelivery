package com.jmarser.mydelivery.presentation.feature_home


import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jmarser.mydelivery.R
import com.jmarser.mydelivery.core.ErrorCodeState
import com.jmarser.mydelivery.presentation.components.CustomSearchBar
import com.jmarser.mydelivery.presentation.components.ScreenEmpty
import com.jmarser.mydelivery.presentation.components.ScreenFailure
import com.jmarser.mydelivery.presentation.components.ScreenLoading
import com.jmarser.mydelivery.presentation.components.SpacerHeightNormal
import com.jmarser.mydelivery.presentation.feature_restaurantDetails.RestaurantDetailsEvent
import com.jmarser.mydelivery.ui.theme.MyDimens
import kotlinx.coroutines.flow.collect

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToRestaurantDetails: (String) -> Unit,
    onNavigateToBack: () -> Unit
) {

    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val categoriesUiState by viewModel.categoryUiState.collectAsStateWithLifecycle()
    val restaurantsUiState by viewModel.restaurantsUiState.collectAsStateWithLifecycle()

    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect { effect ->
            when (effect) {
                is HomeEffect.CategorySelected -> {
                    Toast.makeText(
                        context,
                        "Seleccionastes: ${effect.category.name}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is HomeEffect.RestaurantSelected -> {
                    onNavigateToRestaurantDetails(effect.restaurant.id ?: "")
                }

                HomeEffect.OnBackEffect -> {
                    onNavigateToBack()
                }
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                horizontal = MyDimens.dimens.paddingNormal,
                vertical = MyDimens.dimens.paddingMedium
            )
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = stringResource(R.string.title_home),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.ExtraBold
        )

        CustomSearchBar(
            state = viewModel.searchTextState,
            onClearQuery = {
                viewModel.onEvent(HomeEvent.ClearSearchQuery)
            }
        )

        when (val state = categoriesUiState) {
            CategoriesUiState.Loading -> {
                ScreenLoading(
                    message = stringResource(R.string.loading_categories)
                )
            }

            CategoriesUiState.Empty -> {
                ScreenEmpty(
                    title = stringResource(R.string.there_are_no_categories_to_display),
                    subTitle = stringResource(R.string.please_try_again_later),
                    onNavigateToBack = {
                        onNavigateToBack()
                    }
                )
            }

            is CategoriesUiState.Failure -> {
                ScreenFailure(
                    isNetwork = state.isNetworkError ?: false,
                    message = stringResource(state.errorCodeState.resourceId),
                    loading = state is CategoriesUiState.Loading,
                    onRetry = {},
                    onNavigateToBack = {
                        onNavigateToBack()
                    }
                )
            }

            is CategoriesUiState.Success -> {
                val categories = state.data?.data.orEmpty()
                if (categories.isNotEmpty()) {
                    CategoriesList(
                        categories = categories,
                        selectedCategory = uiState.selectedCategory,
                        onCategorySelected = {
                            viewModel.onEvent(HomeEvent.OnCategorySelected(it))
                        }
                    )
                } else {
                    ScreenEmpty(
                        title = stringResource(R.string.there_are_no_categories_to_display),
                        subTitle = stringResource(R.string.please_try_again_later),
                        onNavigateToBack = {
                            onNavigateToBack()
                        }
                    )
                }
            }

            else -> {
                ScreenFailure(
                    isNetwork = false,
                    message = stringResource(ErrorCodeState.UNKNOWN_ERROR.resourceId),
                    loading = state is CategoriesUiState.Loading,
                    onRetry = {},
                    onNavigateToBack = {
                        onNavigateToBack()
                    }
                )
            }
        }

        SpacerHeightNormal()

        when (val state = restaurantsUiState) {
            is RestaurantsUiState.Success -> {
                AnimatedContent(
                    targetState = state.data.data,
                    transitionSpec = {
                        fadeIn() togetherWith fadeOut()
                    }
                ) { restaurants ->
                    RestaurantsList(
                        listState = listState,
                        restaurants = restaurants,
                        onRestaurantSelected = {
                            viewModel.onEvent(HomeEvent.OnRestaurantSelected(it))
                        },
                        onFavoriteToggle = {
                            viewModel.onEvent(HomeEvent.ToggleFavoriteRestaurant(it))
                        }
                    )
                }

            }

            is RestaurantsUiState.Failure -> {
                ScreenFailure(
                    isNetwork = false,
                    message = stringResource(ErrorCodeState.UNKNOWN_ERROR.resourceId),
                    loading = state is RestaurantsUiState.Loading,
                    onRetry = {},
                    onNavigateToBack = {
                        onNavigateToBack()
                    }
                )
            }

            RestaurantsUiState.Empty ->{
                ScreenEmpty(
                    title = stringResource(R.string.no_restaurant_to_display),
                    subTitle = stringResource(R.string.please_try_again_later),
                    onNavigateToBack = {
                        onNavigateToBack()
                    }
                )
            }

            else -> {
                ScreenFailure(
                    isNetwork = false,
                    message = stringResource(ErrorCodeState.UNKNOWN_ERROR.resourceId),
                    loading = state is RestaurantsUiState.Loading,
                    onRetry = {},
                    onNavigateToBack = {
                        onNavigateToBack()
                    }
                )}
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        modifier = Modifier,
        onNavigateToRestaurantDetails = {},
        onNavigateToBack = {}
    )
}
