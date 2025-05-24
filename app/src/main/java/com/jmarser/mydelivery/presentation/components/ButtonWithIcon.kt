package com.jmarser.mydelivery.presentation.components


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jmarser.mydelivery.R
import com.jmarser.mydelivery.ui.theme.MyDimens

@Composable
fun ButtonWithIcon(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    message: String,
    onClickAction: () -> Unit
) {
    Button(
        onClick = {
            onClickAction()
        }
    ) {
        Row (
            modifier = Modifier
                .padding(horizontal = MyDimens.dimens.paddingNormal),
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.White
            )

            SpacerWidthNormal()

            Text(
                text = message
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun ButtonWithIconPreview() {
    ButtonWithIcon(
        modifier = Modifier,
        icon = AppIcons.ic_back,
        message = "Volver",
        onClickAction = {}
    )
}
