package com.jmarser.mydelivery.presentation.feature_auth.sign_up


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.jmarser.mydelivery.R
import com.jmarser.mydelivery.presentation.components.AppIcons
import com.jmarser.mydelivery.presentation.components.ButtonForm
import com.jmarser.mydelivery.presentation.components.GroupSocialButtons
import com.jmarser.mydelivery.presentation.components.PasswordInputField
import com.jmarser.mydelivery.presentation.components.RowQuestionWithTextButton
import com.jmarser.mydelivery.presentation.components.SpacerHeightMedium
import com.jmarser.mydelivery.presentation.components.SpacerWidthNormal
import com.jmarser.mydelivery.presentation.components.TextInputField
import com.jmarser.mydelivery.ui.theme.MyDimens
import com.jmarser.mydelivery.ui.theme.Orange_enabled

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .fillMaxSize()
    ){
        Image(
            painter = painterResource(id = R.drawable.ic_auth_bg),
            contentDescription = "Background",
            contentScale = ContentScale.Crop
        )

        Column (
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(MyDimens.dimens.paddingMedium)
        ){

            Spacer(modifier = Modifier.weight(1f))

            Text(
                modifier = Modifier
                    .padding(MyDimens.dimens.paddingMedium),
                text = stringResource(id = R.string.sign_up_title),
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black,
            )

            TextInputField(
                modifier = Modifier,
                value = "",
                title = R.string.name,
                placeholder = R.string.john_doe,
                onValueChange = {},
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
                leadingIcon = AppIcons.ic_user
            )

            TextInputField(
                modifier = Modifier,
                value = "",
                title = R.string.name,
                placeholder = R.string.placeholder_email,
                onValueChange = {},
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next,
                leadingIcon = AppIcons.ic_email
            )

            PasswordInputField(
                modifier = Modifier,
                value = "",
                title = R.string.password,
                onValueChange = {},
                iconShow = AppIcons.ic_eyeOpen,
                iconHide = AppIcons.ic_eyeClosed,
                leadingIcon = AppIcons.ic_password,
            )

            PasswordInputField(
                modifier = Modifier,
                value = "",
                title = R.string.repeat_password,
                onValueChange = {},
                iconShow = AppIcons.ic_eyeOpen,
                iconHide = AppIcons.ic_eyeClosed,
                leadingIcon = AppIcons.ic_password,
            )

            SpacerHeightMedium()

            ButtonForm(
                modifier = Modifier
                    .fillMaxWidth(),
                onClickButtom = {},
                enabled = false,
                loading = false,
                textButton = R.string.sign_Up
            )

            SpacerHeightMedium()

            RowQuestionWithTextButton(
                modifier = Modifier
                    .fillMaxWidth(),
                textQuestion = R.string.have_account,
                textOption = R.string.login,
                onClickText = {}
            )

            SpacerHeightMedium()

            GroupSocialButtons(
                color = Color.Black,
                titleInfo = R.string.sign_in_with,
                onFacebookClick = {},
                onGoogleClick = {}
            )

            SpacerHeightMedium()
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun SignUpScreenPreview() {
    SignUpScreen(modifier = Modifier)
}
