package com.jmarser.mydelivery.presentation.components


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jmarser.mydelivery.domain.modelsDomain.RestaurantDm
import com.jmarser.mydelivery.ui.theme.MyDimens
import com.jmarser.mydelivery.ui.theme.Orange_enabled

@Composable
fun TitleScreenDetails(
    modifier: Modifier = Modifier,
    headerItem: HeaderItem,
    onNavigateToReviews: () -> Unit
) {
    Column (
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = MyDimens.dimens.paddingMedium)
    ){
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MyDimens.dimens.paddingMedium, vertical = MyDimens.dimens.paddingNormal),
            text = headerItem.name ?: "Sin nombre",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.ExtraBold
        )

        SpacerHeightSmall()

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MyDimens.dimens.paddingMedium, vertical = MyDimens.dimens.paddingNormal),
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                modifier = Modifier
                    .size(24.dp),
                imageVector = AppIcons.ic_start,
                contentDescription = null,
                tint = Orange_enabled
            )

            SpacerWidthSmall()

            Text(
                text = "4.5",
                style = MaterialTheme.typography.bodyMedium)

            SpacerWidthSmall()

            Text(
                text = "(30+)",
                style = MaterialTheme.typography.bodyMedium)

            SpacerWidthNormal()

            TextButton(
                onClick = onNavigateToReviews
            ) {
                Text(
                    text = "Todos los comentarios",
                    color = Orange_enabled
                )
            }
        }
    }
}

/*@Preview(showBackground = false)
@Composable
fun TitleScreenDetailsPreview() {
    TitleScreenDetails(
        modifier = Modifier,
        restaurant = RestaurantDm(
            id = "711acc5b-993e-4453-8ca4-76f59cbf6586",
            name = "Pizza Palace",
            address = "123 Main St, New York, NY",
            categoryId = "b9dde0e2-2a66-4071-b04d-6f612a4271e1",
            imageUrl = "https://www.marthastewart.com/thmb/3N-0cJgJfLDyytnCehJd4aVgHJw=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/white-pizza-172-d112100_horiz-c868dcf28ed44b21af90f11797d6d7d6.jpgitokKoRSmCVm",
            isFavorite = false,
            distance = 0.0),
        onNavigateToReviews = {}
    )
}*/
