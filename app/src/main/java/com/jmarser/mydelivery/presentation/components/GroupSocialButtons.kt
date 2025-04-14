package com.jmarser.mydelivery.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jmarser.mydelivery.R
import com.jmarser.mydelivery.ui.theme.MyDimens

/**
 * Project: My Delivery
 * File: GroupSocialButtons
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 10/04/2025
 */
 
@Composable
fun GroupSocialButtons(
    color: Color = Color.White,
    @StringRes titleInfo: Int,
    onFacebookClick: () -> Unit,
    onGoogleClick: () -> Unit
){

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = MyDimens.dimens.paddingMedium)
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            HorizontalDivider(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = MyDimens.dimens.paddingNormal),
                thickness = MyDimens.dimens.borderExtraSmall,
                color = color
            )

            Text(
                text = stringResource(id = titleInfo),
                color = color
            )

            HorizontalDivider(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = MyDimens.dimens.paddingNormal),
                thickness = MyDimens.dimens.borderExtraSmall,
                color = color
            )
        }

        SpacerHeightMedium()

        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ){
            SocialButton(
                icono = AppIcons.facebook(),
                title = R.string.facebook,
                onClick = onFacebookClick
            )

            SocialButton(
                icono = AppIcons.google(),
                title = R.string.google,
                onClick = onGoogleClick
            )

        }

    }
}

@Composable
@Preview
fun GroupSocialButtonsPreview(){
    GroupSocialButtons(
        titleInfo = R.string.sign_in_with,
        onFacebookClick = {},
        onGoogleClick = {}
    )
}