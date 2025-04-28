package com.jmarser.mydelivery.presentation.feature_auth.sign_in


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jmarser.mydelivery.R
import com.jmarser.mydelivery.presentation.components.AppIcons
import com.jmarser.mydelivery.presentation.components.ButtonForm
import com.jmarser.mydelivery.presentation.components.CustomNotificationDialog
import com.jmarser.mydelivery.presentation.components.GroupSocialButtons
import com.jmarser.mydelivery.presentation.components.PasswordInputField
import com.jmarser.mydelivery.presentation.components.RowQuestionWithTextButton
import com.jmarser.mydelivery.presentation.components.SpacerHeightMedium
import com.jmarser.mydelivery.presentation.components.TextInputField
import com.jmarser.mydelivery.ui.theme.MyDimens
import com.jmarser.mydelivery.ui.theme.Orange_enabled

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel = hiltViewModel(),
    onNavigateToSignUp: () -> Unit,
    onNavigateToHome: () -> Unit
) {

    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val formState by viewModel.formState.collectAsStateWithLifecycle()
    val dialogState by viewModel.dialogState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = true) {
        viewModel.effect.collect{effect ->
            when(effect){
                SignInEffect.ClearForm -> viewModel.clearForm()
                SignInEffect.NavigateToHome -> onNavigateToHome()
                is SignInEffect.ShowToast -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    if(dialogState.isVisible){
        CustomNotificationDialog(
            textTitle = dialogState.title,
            textMessage = dialogState.message,
            onConfirm = {
                viewModel.onEvent(SignInEvent.DismissDialog)
            },
            onDismiss = {
                viewModel.onEvent(SignInEvent.DismissDialog)
            }
        )
    }

    Box(
        modifier = modifier
        .fillMaxSize()
    ){
        Image(
            painter = painterResource(id = R.drawable.ic_auth_bg),
            contentScale = ContentScale.Crop,
            contentDescription = "Background"
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
                text = stringResource(id = R.string.sign_in_title),
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black
            )

            TextInputField(
                modifier = Modifier,
                value = formState.email,
                title = R.string.name,
                placeholder = R.string.placeholder_email,
                onValueChange = {
                    viewModel.onEvent(SignInEvent.SetEmail(it))
                },
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next,
                leadingIcon = AppIcons.ic_email,
                errorMessage = formState.emailErrorMessage
            )

            PasswordInputField(
                modifier = Modifier,
                value = formState.password,
                title = R.string.password,
                onValueChange = {
                    viewModel.onEvent(SignInEvent.SetPassword(it))
                },
                iconShow = AppIcons.ic_eyeOpen,
                iconHide = AppIcons.ic_eyeClosed,
                leadingIcon = AppIcons.ic_password,
                errorMessage = formState.passwordErrorMessage
            )

            TextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MyDimens.dimens.paddingMedium),
                onClick = {}
            ) {
                Text(
                    text = stringResource(id = R.string.forgot_password),
                    color = Orange_enabled
                )
            }

            SpacerHeightMedium()

            ButtonForm(
                modifier = Modifier
                    .fillMaxWidth(),
                onClickButtom = {
                    viewModel.onEvent(SignInEvent.SignInButtonPressed)
                },
                enabled = formState.isSubmitButtonEnabled,
                loading = uiState is SignInUiState.Loading,
                textButton = R.string.sign_in
            )

            SpacerHeightMedium()

            RowQuestionWithTextButton(
                modifier = Modifier
                    .fillMaxWidth(),
                textQuestion = R.string.dont_have_account,
                textOption = R.string.sign_Up,
                onClickText = { onNavigateToSignUp() }
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
fun SignInScreenPreview() {
    SignInScreen(
        modifier = Modifier,
        onNavigateToSignUp = {},
        onNavigateToHome = {}
    )
}
