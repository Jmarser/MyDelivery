package com.jmarser.mydelivery.presentation.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.room.util.TableInfo
import com.jmarser.mydelivery.MyDelivery
import com.jmarser.mydelivery.R
import com.jmarser.mydelivery.ui.theme.MyDimens

@Composable
fun ScreenEmpty(
    modifier: Modifier = Modifier,
    title: String = stringResource(id = R.string.title_data_empty),
    subTitle: String = stringResource(id = R.string.sub_title_data_empty),
    onNavigateToBack: () -> Unit
) {
    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(MyDimens.dimens.paddingLarge),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Icon(
            modifier = Modifier
                .size(MyDimens.dimens.iconSizeExtraLarge),
            imageVector = AppIcons.ic_empty,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = .5f)
        )

        SpacerHeightNormal()

        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium
        )

        SpacerHeightSmall()

        Text(
            text = subTitle,
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center
        )

        SpacerHeightMedium()

        ButtonWithIcon(
            icon = AppIcons.ic_back,
            message = stringResource(id = R.string.volver),
            onClickAction = {onNavigateToBack()}
        )

    }
}

@Preview(showSystemUi = true)
@Composable
fun ScreenEmptyPreview() {
    ScreenEmpty(
        modifier = Modifier,
        onNavigateToBack = {}
    )
}
