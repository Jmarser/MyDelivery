package com.jmarser.mydelivery.presentation.feature_restaurantDetails


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jmarser.mydelivery.R
import com.jmarser.mydelivery.presentation.components.HeaderScreenDetails
import com.jmarser.mydelivery.presentation.components.TitleScreenDetails
import com.jmarser.mydelivery.ui.theme.MyDimens

@Composable
fun RestaurantDetailsScreen(
    modifier: Modifier = Modifier,
    restaurantId: String,
    viewModel: RestaurantDetailsViewModel = hiltViewModel(),
    onNavigateToBack: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val dishesState by viewModel.dishesUiState.collectAsStateWithLifecycle()

    when(val details = uiState){
        RestaurantDetailsUiState.Empty -> {
            Text(
                text = "Restaurante no encontrado"
            )
        }
        is RestaurantDetailsUiState.Failure -> {
            Text(
                text = "Error desconocido"
            )
        }
        RestaurantDetailsUiState.Loading -> {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
            )
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
                item (
                    span = {
                        GridItemSpan(2)
                    }
                ){
                    HeaderScreenDetails(
                        restaurant = details.data,
                        onNavigateToBack = {
                            onNavigateToBack()
                        },
                        onFavoriteToggle = {

                        }
                    )
                }

                item (
                    span = {
                        GridItemSpan(2)
                    }
                ){
                    TitleScreenDetails(
                        restaurant = details.data,
                        onNavigateToReviews = {}
                    )
                }

                item (
                    span = {
                        GridItemSpan(2)
                    }
                ){
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = MyDimens.dimens.paddingLarge),
                        text = stringResource(id = R.string.lorem_ipsum),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                when(val dishes = dishesState){
                    DishesUiState.Loading -> {
                        item (
                            span = {
                                GridItemSpan(2)
                            }
                        ){
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                                ){
                                CircularProgressIndicator()
                            }
                        }
                    }
                    DishesUiState.Empty -> {

                        item (
                            span = {
                                GridItemSpan(2)
                            }
                        ){
                            Text(
                                text = "No hay platos para mostrar"
                            )
                        }
                    }
                    is DishesUiState.Failure -> {
                        item (
                            span = {
                                GridItemSpan(2)
                            }
                        ){
                            Text(
                                text = "Error desconocido"
                            )
                        }

                    }
                    is DishesUiState.Success -> {

                        val dis = dishes.data.data.orEmpty().filterNotNull()

                        items(dis){dish ->
                            DishesItem(
                                dish = dish,
                                onDishSelected = {},
                                onToggleFavoriteDish = {}
                            )
                        }
                    }
                    else -> {
                        item (
                            span = {
                                GridItemSpan(2)
                            }
                        ){
                            Text(
                                text = "Error desconocido"
                            )
                        }
                    }
                }
            }
        }

        else -> {
            Text(
                text = "Error desconocido"
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
        onNavigateToBack = {}
    )
}
