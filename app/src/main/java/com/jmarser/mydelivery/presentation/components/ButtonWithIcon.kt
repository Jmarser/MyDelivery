package com.jmarser.mydelivery.presentation.components


import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import com.jmarser.mydelivery.ui.theme.Orange_disabled
import com.jmarser.mydelivery.ui.theme.Orange_enabled

@Composable
fun ButtonWithIcon(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    message: String,
    loading: Boolean = false,
    onClickAction: () -> Unit
) {
    Button(
        modifier = Modifier
            .height(MyDimens.dimens.buttonHeightNormal)
            .padding(horizontal = MyDimens.dimens.spacerXXL),
        onClick = {
            onClickAction()
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Orange_enabled,
            disabledContainerColor = Orange_disabled
        ),
        border = BorderStroke(
            width = MyDimens.dimens.borderExtraSmall,
            color = Orange_enabled
        )
    ) {
        Box(){
            AnimatedContent(
                targetState = loading,
                transitionSpec = {
                    fadeIn(animationSpec = tween(300)) + scaleIn(initialScale = .8f) togetherWith fadeOut(animationSpec = tween(300)) + scaleOut(targetScale = .8f)
                }
            ) {target ->
                if (target){
                    CircularProgressIndicator(
                        modifier = Modifier
                            .padding(horizontal = MyDimens.dimens.paddingLarge)
                            .size(MyDimens.dimens.iconSizeLarge),
                        color = Color.White
                    )
                }else{
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
        loading = false,
        onClickAction = {}
    )
}
