package com.jmarser.mydelivery.presentation.feature_restaurantDetails


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import com.jmarser.mydelivery.R
import com.jmarser.mydelivery.domain.modelsDomain.DishItemDm
import com.jmarser.mydelivery.presentation.components.AppIcons
import com.jmarser.mydelivery.presentation.components.SpacerHeightNormal
import com.jmarser.mydelivery.presentation.components.SpacerHeightSmall
import com.jmarser.mydelivery.presentation.components.SpacerWidthSmall
import com.jmarser.mydelivery.ui.theme.MyDimens
import com.jmarser.mydelivery.ui.theme.Orange_enabled

@Composable
fun DishesItem(
    modifier: Modifier = Modifier,
    dish: DishItemDm,
    onDishSelected: (DishItemDm) -> Unit,
    onToggleFavoriteDish: (DishItemDm) -> Unit
) {

    val IMAGE_BOX_SIZE = 200.dp
    val TEXT_BOX_SIZE = 50.dp

    Column (
        modifier = modifier
            .fillMaxWidth()
            .height(IMAGE_BOX_SIZE + TEXT_BOX_SIZE)
            .border(
                width = MyDimens.dimens.borderExtraSmall,
                color = Color.LightGray,
                shape = MaterialTheme.shapes.medium
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box(
            modifier = Modifier
                .height(IMAGE_BOX_SIZE)
                .shadow(
                    elevation = MyDimens.dimens.cardElevationLarge,
                    shape = MaterialTheme.shapes.extraLarge
                )
                .background(color = Color.White)
                .clip(MaterialTheme.shapes.medium)
        ){
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        onDishSelected(dish)
                    },
                model = dish.imageUrl,
                contentDescription = dish.name,
                contentScale = ContentScale.Crop,
                error = painterResource(id = R.drawable.ic_image_not_found),
                placeholder = painterResource(id = R.drawable.background)
            )

            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(MyDimens.dimens.paddingNormal)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = .7f))
                    .padding(MyDimens.dimens.paddingNormal)
            ){
                Text(
                    text = "${dish.price} €",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            IconButton(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = MyDimens.dimens.paddingMedium, end = MyDimens.dimens.paddingMedium)
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.7f)),
                onClick = {
                    onToggleFavoriteDish(dish)
                }
            ) {
                Icon(
                    imageVector = if (dish.isFavorite) AppIcons.ic_yes_favorite else AppIcons.ic_not_favorite,
                    contentDescription = "favorito",
                    tint = if (dish.isFavorite) Color.Red else Color.Black
                )
            }

            Row (
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = MyDimens.dimens.paddingNormal, bottom = MyDimens.dimens.paddingNormal)
                    .clip(CircleShape)
                    .background(
                        Color.White.copy(alpha = .7f)
                    )
                    .padding(horizontal = MyDimens.dimens.paddingNormal, vertical = MyDimens.dimens.paddingNormal),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "4,5",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold
                )
                SpacerHeightSmall()
                Icon(
                    imageVector = AppIcons.ic_start,
                    contentDescription = "Estrella",
                    modifier = Modifier.size(14.dp),
                    tint = Orange_enabled
                )
                SpacerHeightSmall()
                Text(
                    text = "(21)",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
        SpacerHeightNormal()

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(TEXT_BOX_SIZE)
                .padding(horizontal = MyDimens.dimens.paddingSmall),
            contentAlignment = Alignment.TopCenter
        ){
            Text(
                text = dish.name ?: "Sin nombre",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Black,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun DishesItemPreview() {
    DishesItem(
        modifier = Modifier,
        dish = DishItemDm(
            id = "20ddf4cf-82d8-4ee6-ac68-a3cf74aa7f95",
            name = "Veggie Burger",
            description = "Grilled veggie patty with avocado",
            price = 9.99,
            imageUrl = "https://www.foodandwine.com/thmb/pwFie7NRkq4SXMDJU6QKnUKlaoI=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/Ultimate-Veggie-Burgers-FT-Recipe-0821-5d7532c53a924a7298d2175cf1d4219f.jpg",
            isFavorite = false
        ),
        onDishSelected = {},
        onToggleFavoriteDish = {}
    )
}
