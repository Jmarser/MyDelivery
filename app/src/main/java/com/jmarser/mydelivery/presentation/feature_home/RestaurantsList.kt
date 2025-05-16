package com.jmarser.mydelivery.presentation.feature_home


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jmarser.mydelivery.R
import com.jmarser.mydelivery.domain.modelsDomain.RestaurantDm
import com.jmarser.mydelivery.presentation.components.AppIcons
import com.jmarser.mydelivery.presentation.components.SpacerHeightLarge
import com.jmarser.mydelivery.presentation.components.SpacerHeightNormal
import com.jmarser.mydelivery.ui.theme.MyDimens
import com.jmarser.mydelivery.ui.theme.Orange_enabled

@Composable
fun RestaurantsList(
    modifier: Modifier = Modifier,
    listState: LazyListState,
    restaurants: List<RestaurantDm>,
    onRestaurantSelected: (RestaurantDm) -> Unit,
    onFavoriteToggle: (RestaurantDm) -> Unit
) {

    Column (
        modifier = modifier
    ){
        Row (
            modifier = Modifier
                .padding(horizontal = MyDimens.dimens.paddingNormal),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                modifier = Modifier
                    .padding(MyDimens.dimens.paddingSmall),
                text = stringResource(id = R.string.popular_restaurants),
                fontWeight = FontWeight.Black,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.weight(1f))

            Row (
                modifier = Modifier
                    .clickable {  },
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = stringResource(id = R.string.view_all),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Orange_enabled
                )

                Icon(
                    modifier = Modifier
                        .size(MyDimens.dimens.iconSizeExtraSmall),
                    imageVector = AppIcons.ic_arrow_right,
                    contentDescription = null,
                    tint = Orange_enabled
                )
            }
        }

        SpacerHeightNormal()

        LazyRow (
            modifier = Modifier.height(229.dp),
            state = listState
        ){
            items(restaurants, key = {it.id!!}){restaurant ->

                AnimatedVisibility(
                    visible = true,
                    enter = fadeIn() + slideInVertically(),
                    exit = fadeOut()
                ) {
                    RestaurantItem(
                        modifier = Modifier
                            .animateItem(
                                fadeInSpec = tween(1000),
                                fadeOutSpec = tween(1000)
                            ),
                        restaurant = restaurant,
                        onRestaurantSelected = onRestaurantSelected,
                        onFavoriteToggle = onFavoriteToggle,
                        isFavorite = restaurant.isFavorite
                    )
                }

            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun RestaurantsListPreview() {
    RestaurantsList(
        modifier = Modifier,
        restaurants = listOf(
            RestaurantDm(
                id = "711acc5b-993e-4453-8ca4-76f59cbf6586",
                name = "Pizza Palace",
                address = "123 Main St, New York, NY",
                categoryId = "b9dde0e2-2a66-4071-b04d-6f612a4271e1",
                imageUrl = "https://www.marthastewart.com/thmb/3N-0cJgJfLDyytnCehJd4aVgHJw=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/white-pizza-172-d112100_horiz-c868dcf28ed44b21af90f11797d6d7d6.jpgitokKoRSmCVm",
                distance = 0.0
            ),
            RestaurantDm(
                id = "711acc5b-993e-4453-8ca4-76f59cbf6586",
                name = "Healthy Bites",
                address = "321 Oak St, Miami, FL",
                categoryId = "b9dde0e2-2a66-4071-b04d-6f612a4271e1",
                imageUrl = "https://i2.wp.com/www.downshiftology.com/wp-content/uploads/2019/04/Cobb-Salad-main.jpg",
                distance = 0.0
            ),
            RestaurantDm(
                id = "711acc5b-993e-4453-8ca4-76f59cbf6586",
                name = "Coffee Corner",
                address = "987 Cedar St, San Francisco, CA",
                categoryId = "b9dde0e2-2a66-4071-b04d-6f612a4271e1",
                imageUrl = "https://insanelygoodrecipes.com/wp-content/uploads/2020/07/Cup-Of-Creamy-Coffee.png",
                distance = 0.0
            ),
            RestaurantDm(
                id = "711acc5b-993e-4453-8ca4-76f59cbf6586",
                name = "Sushi Express",
                address = "654 Maple St, Seattle, WA",
                categoryId = "b9dde0e2-2a66-4071-b04d-6f612a4271e1",
                imageUrl = "https://tb-static.uber.com/prod/image-proc/processed_images/87baf961b666795ea98160dc3b1d465c/fb86662148be855d931b37d6c1e5fcbe.jpeg",
                distance = 0.0
            ),
            RestaurantDm(
                id = "711acc5b-993e-4453-8ca4-76f59cbf6586",
                name = "Burger Haven",
                address = "456 Elm St, Los Angeles, CA",
                categoryId = "b9dde0e2-2a66-4071-b04d-6f612a4271e1",
                imageUrl = "https://imageproxy.wolt.com/mes-image/43bb7be3-03c2-4337-9d52-99cba2b1650d/85493202-0013-44f0-b7c1-59262d53e9ff",
                distance = 0.0
            )
        ),
        onRestaurantSelected = {},
        onFavoriteToggle = {},
        listState = rememberLazyListState()
    )
}
