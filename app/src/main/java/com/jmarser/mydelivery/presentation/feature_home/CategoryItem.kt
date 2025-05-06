package com.jmarser.mydelivery.presentation.feature_home


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.jmarser.mydelivery.R
import com.jmarser.mydelivery.domain.modelsDomain.CategoryDm
import com.jmarser.mydelivery.ui.theme.MyDimens
import com.jmarser.mydelivery.ui.theme.Orange_enabled

@Composable
fun CategoryItem(
    category: CategoryDm,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column (
        modifier = Modifier
            .padding(MyDimens.dimens.paddingNormal)
            .height(90.dp)
            .width(60.dp)
            .clickable { onClick() }
            .shadow(
                elevation = MyDimens.dimens.cardElevationNormal,
                shape = MaterialTheme.shapes.extraLarge,
                ambientColor = Color.Gray.copy(alpha = .8f),
                spotColor = Color.Gray.copy(alpha = .8f)
            )
            .border(
                width = MyDimens.dimens.borderExtraSmall,
                color = if (isSelected) Orange_enabled else Color.Gray.copy(alpha = .4f),
                shape = MaterialTheme.shapes.extraLarge
            )
            .background(
                color = if (isSelected) Orange_enabled else Color.White,
                shape = MaterialTheme.shapes.extraLarge
            )
            .clip(MaterialTheme.shapes.extraLarge)
            .padding(MyDimens.dimens.paddingNormal),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        AsyncImage(
            modifier = Modifier
                .size(40.dp)
                .background(
                    color = Color.White,
                    shape = CircleShape
                )
                .clip(CircleShape),
            model = category.imageUrl,
            contentDescription = category.name,
            contentScale = ContentScale.Inside,
            placeholder = painterResource(R.drawable.ic_image),
            error = painterResource(R.drawable.ic_image_not_found)
        )

        Spacer(modifier = Modifier.weight(1f))

        category.name?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryItemPreview() {
    CategoryItem(
        category = CategoryDm(
            id = "273ce30c-3b58-4965-b492-057e9e75c17c",
            name = "Burger",
            imageUrl = "https://www.pngarts.com/files/3/Fast-Food-Free-PNG-Image.png"
        ),
        isSelected = false,
        onClick = {}
    )
}
