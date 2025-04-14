package com.jmarser.mydelivery.presentation.components


import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jmarser.mydelivery.R
import com.jmarser.mydelivery.ui.theme.MyDimens
import com.jmarser.mydelivery.ui.theme.Orange_disabled
import com.jmarser.mydelivery.ui.theme.Orange_enabled

@Composable
fun TextInputField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    @StringRes title: Int? = null,
    @StringRes placeholder: Int? = null,
    keyboardType: KeyboardType = KeyboardType.Email,
    imeAction: ImeAction = ImeAction.Done,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    showTrailingIcon: Boolean = false,
    onTrailingIconClick: () -> Unit = {},
    errorMessage: String? = null
) {

    val keyboardController = LocalSoftwareKeyboardController.current

    Column (
        modifier = modifier
            .padding(top = MyDimens.dimens.paddingMedium)
            .padding(bottom = if (!errorMessage.isNullOrEmpty()) 10.dp else 20.dp)
    ){
        title?.let {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = MyDimens.dimens.spacerNormal)
                    .padding(bottom = MyDimens.dimens.spacerNormal),
                text = stringResource(title),
                color = Orange_enabled
            )
        }

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    if (!focusState.isFocused){
                        keyboardController?.hide()
                    }
                },
            value = value,
            onValueChange = onValueChange,
            placeholder = if (placeholder != null) {
                {
                    Text(
                        text = stringResource(id = placeholder)
                    )
                }
            }else null,
            shape = RoundedCornerShape(MyDimens.dimens.roundedShapePercent25),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            singleLine = true,
            maxLines = 1,
            leadingIcon = if (leadingIcon != null){
                {
                    Icon(
                        modifier = Modifier
                            .width(MyDimens.dimens.iconSizeSmall)
                            .height(MyDimens.dimens.iconSizeSmall),
                        imageVector = leadingIcon,
                        contentDescription = null,
                        tint = Orange_enabled
                    )
                }
            }else null,
            trailingIcon = if (showTrailingIcon){
                {
                    IconButton(
                        onClick = { onTrailingIconClick() }
                    ) {
                        if (trailingIcon != null){
                            Icon(
                                modifier = Modifier
                                    .width(MyDimens.dimens.iconSizeSmall)
                                    .height(MyDimens.dimens.iconSizeSmall),
                                imageVector = trailingIcon,
                                contentDescription = null,
                                tint = Orange_enabled
                            )
                        }
                    }
                }
            }else null,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                cursorColor = Orange_enabled,
                focusedLabelColor = Orange_enabled,
                unfocusedLabelColor = Orange_disabled,
                focusedIndicatorColor = Orange_enabled,
                unfocusedIndicatorColor = Orange_enabled
            )
        )

        if (!errorMessage.isNullOrEmpty()){
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = MyDimens.dimens.spacerSmall)
                    .padding(end = MyDimens.dimens.paddingMedium),
                text = errorMessage,
                color = Color.Red,
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.bodySmall
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun TextInputFieldPreview() {
    TextInputField(
        modifier = Modifier,
        value = "",
        title = R.string.name,
        placeholder = R.string.john_doe,
        onValueChange = {},
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Next,
        errorMessage = "Mensaje de error",
        showTrailingIcon = true,
        leadingIcon = AppIcons.ic_user,
        trailingIcon = AppIcons.ic_info,
    )
}
