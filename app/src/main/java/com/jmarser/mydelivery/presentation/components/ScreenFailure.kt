package com.jmarser.mydelivery.presentation.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.jmarser.mydelivery.R
import com.jmarser.mydelivery.core.ErrorCodeState
import com.jmarser.mydelivery.ui.theme.MyDimens

@Composable
fun ScreenFailure(
    modifier: Modifier = Modifier,
    isNetwork: Boolean = false,
    message: String = stringResource(id = ErrorCodeState.UNKNOWN_ERROR.resourceId),
    onRetry: () -> Unit,
    onNavigateToBack: () -> Unit
) {

    val icon = if (isNetwork){
        AppIcons.ic_not_network
    }else{
        AppIcons.ic_error_server
    }

    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(MyDimens.dimens.paddingLarge),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Icon(
            modifier = Modifier
                .size(64.dp),
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.error
        )

        SpacerHeightMedium()

        Text(
            text = message,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )

        SpacerHeightMedium()

        Row (
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Absolute.SpaceAround
        ){

            ButtonWithIcon(
                icon = AppIcons.ic_back,
                message = stringResource(id = R.string.volver),
                onClickAction = {onNavigateToBack()}
            )

            ButtonWithIcon(
                icon = AppIcons.ic_retry,
                message = stringResource(id = R.string.retry),
                onClickAction = {onRetry()}
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ScreenFailurePreview() {
    ScreenFailure(
        modifier = Modifier,
        onRetry = {},
        onNavigateToBack = {}
    )
}
