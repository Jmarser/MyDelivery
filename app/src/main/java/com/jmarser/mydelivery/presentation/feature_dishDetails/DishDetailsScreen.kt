package com.jmarser.mydelivery.presentation.feature_dishDetails


import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jmarser.mydelivery.R
import com.jmarser.mydelivery.core.ErrorCodeState
import com.jmarser.mydelivery.presentation.components.AppIcons
import com.jmarser.mydelivery.presentation.components.ButtonWithIcon
import com.jmarser.mydelivery.presentation.components.HeaderItem
import com.jmarser.mydelivery.presentation.components.HeaderScreenDetails
import com.jmarser.mydelivery.presentation.components.ScreenEmpty
import com.jmarser.mydelivery.presentation.components.ScreenFailure
import com.jmarser.mydelivery.presentation.components.ScreenLoading
import com.jmarser.mydelivery.presentation.components.SpacerHeightMedium
import com.jmarser.mydelivery.presentation.components.SpacerWidthNormal
import com.jmarser.mydelivery.presentation.components.TitleScreenDetails
import com.jmarser.mydelivery.ui.theme.MyDimens
import com.jmarser.mydelivery.ui.theme.Orange_enabled
import kotlinx.coroutines.flow.collect

@Composable
fun DishDetailsScreen(
    modifier: Modifier = Modifier,
    dishId: String,
    viewModel: DishDetailsViewModel = hiltViewModel(),
    onNavigateToBack: () -> Unit
) {

    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val count = remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect{effect ->
            when(effect){
                DishDetailsEffect.NavigateToBack -> onNavigateToBack()
                is DishDetailsEffect.ShowToast -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    when(val details = uiState){
        DishDetailsUiState.Empty -> {
            ScreenEmpty(
                title = stringResource(R.string.no_data_to_display),
                subTitle = stringResource(R.string.please_try_again_later),
                onNavigateToBack = {
                    viewModel.onEvent(DishDetailsEvent.OnNavigateToBack)
                }
            )
        }
        DishDetailsUiState.Loading -> {
            ScreenLoading(
                message = stringResource(R.string.loading_dish_details)
            )
        }
        is DishDetailsUiState.Failure -> {
            ScreenFailure(
                isNetwork = details.isNetwork ?: false,
                message = stringResource(details.errorCodeState.resourceId),
                onRetry = {
                    viewModel.onEvent(DishDetailsEvent.OnRetry)
                },
                onNavigateToBack = {
                    viewModel.onEvent(DishDetailsEvent.OnNavigateToBack)
                }
            )
        }
        is DishDetailsUiState.Success -> {

            Column (
                modifier = modifier
                    .padding(WindowInsets.systemBars.asPaddingValues())
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                HeaderScreenDetails(
                    headerItem = HeaderItem.Dish(details.data),
                    onFavoriteToggle = {
                        when(it){
                            is HeaderItem.Dish -> {
                                viewModel.onEvent(DishDetailsEvent.ToggleFavoriteDish(it.data))
                            }
                            is HeaderItem.Restaurant -> {
                                // No hacemos nada
                            }
                        }
                    },
                    onNavigateToBack = {
                        viewModel.onEvent(DishDetailsEvent.OnNavigateToBack)
                    }
                )

                SpacerHeightMedium()

                TitleScreenDetails(
                    headerItem = HeaderItem.Dish(details.data),
                    onNavigateToReviews = {}
                )

                SpacerHeightMedium()

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = MyDimens.dimens.paddingLarge),
                    text = details.data.description ?: stringResource(R.string.lorem_ipsum),
                    style = MaterialTheme.typography.bodyMedium
                )

                SpacerHeightMedium()

                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(MyDimens.dimens.paddingMedium),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    SpacerWidthNormal()

                    Text(
                        text = "${details.data.price} €",
                        color = Orange_enabled,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )

                    Spacer(Modifier.weight(1f))

                    Row (
                        modifier = Modifier,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        IconButton(
                            onClick = {
                                count.value -= 1
                            }
                        ) {
                            Icon(
                                imageVector = AppIcons.ic_minus,
                                contentDescription = null,
                                tint = Orange_enabled
                            )
                        }

                        SpacerWidthNormal()

                        Text(
                            text = "${count.value}",
                        )

                        SpacerWidthNormal()

                        IconButton(
                            onClick = {
                                count.value += 1
                            }
                        ) {
                            Icon(
                                imageVector = AppIcons.ic_add,
                                contentDescription = null,
                                tint = Orange_enabled
                            )
                        }
                    }
                }

                Spacer((Modifier.weight(1f)))

                ButtonWithIcon(
                    modifier = Modifier
                        .fillMaxWidth(),
                    icon = AppIcons.ic_cart,
                    onClickAction = {},
                    message = stringResource(R.string.add_to_cart),
                    loading = false
                )
            }
        }
        else -> {
            ScreenFailure(
                isNetwork = false,
                message = stringResource(ErrorCodeState.UNKNOWN_ERROR.resourceId),
                onRetry = {},
                onNavigateToBack = {
                    onNavigateToBack()
                }
            )
        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun DishDetailsScreenPreview() {
    DishDetailsScreen(
        modifier = Modifier,
        dishId = "",
        onNavigateToBack = {}
    )
}
