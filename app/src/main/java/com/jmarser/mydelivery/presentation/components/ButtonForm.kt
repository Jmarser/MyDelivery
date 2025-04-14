package com.jmarser.mydelivery.presentation.components


import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.AnimationConstants
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jmarser.mydelivery.R
import com.jmarser.mydelivery.ui.theme.MyDimens
import com.jmarser.mydelivery.ui.theme.Orange_disabled
import com.jmarser.mydelivery.ui.theme.Orange_enabled

@Composable
fun ButtonForm(
    modifier: Modifier = Modifier,
    onClickButtom: () -> Unit,
    enabled: Boolean = false,
    loading: Boolean = false,
    @StringRes textButton: Int
) {
    Button(
        modifier = modifier
            .height(MyDimens.dimens.buttonHeightNormal)
            .padding(horizontal = MyDimens.dimens.spacerXXL),
        colors = ButtonDefaults.buttonColors(
            containerColor = Orange_enabled,
            disabledContainerColor = Orange_disabled
        ),
        onClick = onClickButtom,
        enabled = enabled,
        border = BorderStroke(
            width = MyDimens.dimens.borderExtraSmall,
            color = Orange_enabled
        )
    ) {
        Box(){
            AnimatedContent(targetState = loading, transitionSpec = {
                fadeIn(animationSpec = tween(300)) + scaleIn(initialScale = .8f) togetherWith fadeOut(animationSpec = tween(300)) + scaleOut(targetScale = .8f)
            }){target ->
                if(target){
                    CircularProgressIndicator(
                        modifier = Modifier
                            .padding(horizontal = MyDimens.dimens.paddingLarge)
                            .size(40.dp),
                        color = Color.White
                    )
                }else {
                    Text(
                        modifier = Modifier
                            .padding( horizontal = MyDimens.dimens.paddingLarge),
                        text = stringResource(id = textButton),
                        color = if (enabled) Color.White else Orange_enabled
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonFormPreview() {
    ButtonForm(
        modifier = Modifier,
        onClickButtom = {},
        enabled = true,
        loading = true,
        textButton = R.string.sign_Up
    )
}
