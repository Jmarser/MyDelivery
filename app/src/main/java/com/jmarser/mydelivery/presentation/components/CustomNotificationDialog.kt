package com.jmarser.mydelivery.presentation.components


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.jmarser.mydelivery.R
import com.jmarser.mydelivery.ui.theme.MyDimens
import com.jmarser.mydelivery.ui.theme.Orange_enabled

@Composable
fun CustomNotificationDialog(
    modifier: Modifier = Modifier,
    textTitle: String? = null,
    textMessage: String,
    textBtnConfirm: String = stringResource(id = R.string.understood),
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {

    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        Card (
            modifier = modifier
                .fillMaxWidth()
                .wrapContentWidth()
                .padding(MyDimens.dimens.paddingMedium),
            shape = MaterialTheme.shapes.large,
            border = BorderStroke(
                width = MyDimens.dimens.borderExtraSmall,
                color = Orange_enabled
            )
        ){
            Column (
                modifier = modifier
                    .wrapContentWidth()
                    .padding(MyDimens.dimens.paddingMedium),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                if (!textTitle.isNullOrEmpty()){
                    SpacerHeightNormal()

                    Text(
                        text = textTitle,
                        style = MaterialTheme.typography.titleLarge
                    )
                }

                SpacerHeightMedium()

                Text(
                    text = textMessage,
                    style = MaterialTheme.typography.bodyMedium
                )

                SpacerHeightMedium()

                Row (
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(MyDimens.dimens.paddingNormal),
                    horizontalArrangement = Arrangement.Center
                ){
                    TextButton(
                        onClick = { onConfirm() }
                    ) {
                        Text(
                            text = textBtnConfirm,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomNotificationDialogPreview() {
    CustomNotificationDialog(
        modifier = Modifier,
        textTitle = "TITULO",
        textMessage = "Mensaje de la notificación",
        textBtnConfirm = "Acepto",
        onConfirm = {},
        onDismiss = {}
    )
}
