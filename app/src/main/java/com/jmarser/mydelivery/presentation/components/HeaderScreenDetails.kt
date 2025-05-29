package com.jmarser.mydelivery.presentation.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.jmarser.mydelivery.R
import com.jmarser.mydelivery.domain.modelsDomain.RestaurantDm
import com.jmarser.mydelivery.ui.theme.MyDimens

@Composable
fun HeaderScreenDetails(
    modifier: Modifier = Modifier,
    headerItem: HeaderItem,
    onNavigateToBack: () -> Unit,
    onFavoriteToggle: (HeaderItem) -> Unit
) {

    Box (
        modifier = modifier
            .fillMaxWidth()
    ){
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MyDimens.dimens.paddingNormal)
                .clip(MaterialTheme.shapes.medium),
            model = headerItem.imageUrl,
            contentDescription = headerItem.name,
            contentScale = ContentScale.FillWidth,
            placeholder = painterResource(id = R.drawable.background),
            error = painterResource(id = R.drawable.ic_image_not_found)
        )

        IconButton(
            modifier = Modifier
                .padding(MyDimens.dimens.paddingMedium)
                .size(48.dp)
                .align(Alignment.TopStart)
                .background(
                    color = Color.LightGray.copy(alpha = .7f),
                    shape = MaterialTheme.shapes.medium
                ),
            onClick = onNavigateToBack
        ) {
            Image(
                imageVector = AppIcons.ic_back,
                contentDescription = "back"
            )
        }

        IconButton(
            modifier = Modifier
                .padding(MyDimens.dimens.paddingMedium)
                .size(48.dp)
                .align(Alignment.TopEnd)
                .background(
                    color = Color.LightGray.copy(alpha = .7f),
                    shape = CircleShape
                ),
            onClick = {
                onFavoriteToggle(headerItem)
            }
        ) {
            Icon(
                imageVector = if (headerItem.isFavorite) AppIcons.ic_yes_favorite else AppIcons.ic_not_favorite,
                contentDescription = "favorito",
                tint = if (headerItem.isFavorite) Color.Red else Color.Black
            )
        }
    }
}

/*@Preview(showBackground = false)
@Composable
fun HeaderScreenDetailsPreview() {
    HeaderScreenDetails(
        modifier = Modifier,
        restaurant = RestaurantDm(
            id = "711acc5b-993e-4453-8ca4-76f59cbf6586",
            name = "Pizza Palace",
            address = "123 Main St, New York, NY",
            categoryId = "b9dde0e2-2a66-4071-b04d-6f612a4271e1",
            imageUrl = "https://www.marthastewart.com/thmb/3N-0cJgJfLDyytnCehJd4aVgHJw=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/white-pizza-172-d112100_horiz-c868dcf28ed44b21af90f11797d6d7d6.jpgitokKoRSmCVm",
            isFavorite = false,
            distance = 0.0),
        onNavigateToBack = {},
        onFavoriteToggle = {}
    )
}*/
