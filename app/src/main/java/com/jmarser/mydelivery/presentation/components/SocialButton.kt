package com.jmarser.mydelivery.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jmarser.mydelivery.R
import com.jmarser.mydelivery.ui.theme.MyDimens

/**
 * Project: My Delivery
 * File: SocialButton
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 10/04/2025
 */
 
@Composable
fun SocialButton(
    icono: Painter,
    @StringRes title: Int,
    onClick: () -> Unit
){
    Button(
        modifier = Modifier
            .height(MyDimens.dimens.buttonHeightNormal),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White
        ),
        shape = MaterialTheme.shapes.extraLarge,
        border = BorderStroke(
            width = MyDimens.dimens.borderExtraSmall,
            color = Color.DarkGray
        ),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                modifier = Modifier
                    .size(MyDimens.dimens.iconSizeNormal)
                    .padding(end = MyDimens.dimens.paddingNormal),
                painter = icono,
                contentDescription = "Logo social"
            )
            Text(
                text = stringResource(id = title),
                color = Color.Black
            )
        }
    }
}

@Composable
@Preview
fun SocialButtonPreview(){
    SocialButton(
        icono = AppIcons.facebook(),
        title = R.string.facebook,
        onClick = {}
    )
}
