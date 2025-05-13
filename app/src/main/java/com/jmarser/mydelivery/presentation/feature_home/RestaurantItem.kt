package com.jmarser.mydelivery.presentation.feature_home


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.jmarser.mydelivery.R
import com.jmarser.mydelivery.domain.modelsDomain.RestaurantDm
import com.jmarser.mydelivery.presentation.components.AppIcons
import com.jmarser.mydelivery.presentation.components.SpacerWidthNormal
import com.jmarser.mydelivery.ui.theme.MyDimens

@Composable
fun RestaurantItem(
    modifier: Modifier = Modifier,
    restaurant: RestaurantDm,
    onRestaurantSelected: (RestaurantDm) -> Unit
) {

    Box(
        modifier = modifier
            .padding(MyDimens.dimens.paddingNormal)
            .width(250.dp)
            .height(229.dp)
            .shadow(
                elevation = MyDimens.dimens.cardElevationLarge,
                shape = MaterialTheme.shapes.extraLarge
            )
            .background(color = Color.White)
            .clip(MaterialTheme.shapes.extraLarge)
    ){
        AsyncImage(
            modifier = Modifier.fillMaxSize()
                .clickable {
                    onRestaurantSelected(restaurant)
                },
            model = restaurant.imageUrl,
            contentDescription = restaurant.name,
            contentScale = ContentScale.Crop,
            error = painterResource(R.drawable.background)
        )

        Column (
            modifier = Modifier
                .matchParentSize()
        ){
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.Transparent)
                    .padding(
                        horizontal = MyDimens.dimens.paddingMedium,
                        vertical = MyDimens.dimens.paddingNormal
                    ),
                verticalAlignment = Alignment.CenterVertically
            ){
                Row (
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(color = Color.White.copy(alpha = .8f))
                        .padding(
                            horizontal = MyDimens.dimens.paddingNormal,
                            vertical = MyDimens.dimens.paddingSmall
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        modifier = Modifier
                            .background( color = Color.Transparent)
                            .padding(end = MyDimens.dimens.paddingSmall),
                        text = "4.5",
                        fontWeight = FontWeight.ExtraBold,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Black
                    )
                    Image(
                        modifier = Modifier
                            .size(16.dp),
                        imageVector = AppIcons.ic_start,
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(Color.Yellow)
                    )
                    Text(
                        modifier = Modifier
                            .padding(start = MyDimens.dimens.paddingSmall),
                        text = "2.5",
                        fontSize = 10.sp,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Black
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Row (
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(color = Color.White.copy(alpha = .8f))
                        .padding(
                            horizontal = MyDimens.dimens.paddingNormal

                        ),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    IconButton(
                        modifier = Modifier
                            .size(16.dp)
                            .clip(CircleShape)
                            .background(color = Color.White.copy(alpha = .8f)),
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = AppIcons.ic_not_favorite,
                            contentDescription = "favorito",
                            tint = Color.Black
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White)
                    .padding(MyDimens.dimens.paddingNormal)
                    .padding(start = MyDimens.dimens.paddingNormal)
            ){
                Text(
                    text = restaurant.name ?: stringResource(id = R.string.restaurant_not_name),
                    style = MaterialTheme.typography.labelLarge
                )
                Row {
                    Row (
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ){
                        Image(
                            modifier = Modifier
                                .padding(vertical = MyDimens.dimens.paddingNormal)
                                .padding(end = MyDimens.dimens.paddingNormal)
                                .size(12.dp),
                            painter = painterResource(id = R.drawable.ic_delivery),
                            contentDescription = null
                        )
                        Text(
                            text = "Free Delivery",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.LightGray
                        )
                    }

                    SpacerWidthNormal()

                    Row (
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ){
                        Image(
                            modifier = Modifier
                                .padding(vertical = MyDimens.dimens.paddingNormal)
                                .padding(end = MyDimens.dimens.paddingNormal)
                                .size(12.dp),
                            painter = painterResource(id = R.drawable.timer),
                            contentDescription = null
                        )
                        Text(
                            text = "10 - 12 mins",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.LightGray
                        )
                    }
                }
            }
        }
    }

}

@Preview(showBackground = false)
@Composable
fun RestaurantItemPreview() {
    RestaurantItem(
        restaurant = RestaurantDm(
            id = "711acc5b-993e-4453-8ca4-76f59cbf6586",
            name = "Pizza Palace",
            address = "123 Main St, New York, NY",
            categoryId = "b9dde0e2-2a66-4071-b04d-6f612a4271e1",
            imageUrl = "https://www.marthastewart.com/thmb/3N-0cJgJfLDyytnCehJd4aVgHJw=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/white-pizza-172-d112100_horiz-c868dcf28ed44b21af90f11797d6d7d6.jpgitokKoRSmCVm",
            distance = 0.0
        ),
        onRestaurantSelected = {

        }
    )
}
