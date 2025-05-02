package com.jmarser.mydelivery.presentation.feature_auth.welcome

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jmarser.mydelivery.R
import com.jmarser.mydelivery.presentation.components.GroupSocialButtons
import com.jmarser.mydelivery.presentation.components.RowQuestionWithTextButton
import com.jmarser.mydelivery.ui.theme.MyDimens
import com.jmarser.mydelivery.ui.theme.Orange_enabled
import com.jmarser.mydelivery.presentation.components.SpacerHeightMedium
import com.jmarser.mydelivery.presentation.navigation.AppRoutes

/**
 * Project: My Delivery
 * File: WelcomeScreen
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 10/04/2025
 */
 
@Composable
fun WelcomeScreen(
    viewModel: WelcomeViewModel = hiltViewModel(),
    onNavigateToSignIn: () -> Unit,
    onNavigateToSignUp: () -> Unit,
    onNavigateToHome: () -> Unit
){

    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.effect.collect{effect ->
            when(effect){
                WelcomeEffect.NavigateToHome -> onNavigateToHome()
                WelcomeEffect.NavigateToSignIn -> onNavigateToSignIn()
                is WelcomeEffect.ShowToast -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    val systemBarsPadding = WindowInsets.systemBars.asPaddingValues()

    val brush = Brush.verticalGradient(
        colors = listOf(
            Color.Transparent,
            Color.Black
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(systemBarsPadding)
    ){

        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "Background",
            contentScale = ContentScale.FillWidth
        )

        Box(
            modifier = Modifier
                .matchParentSize()
                .background(brush = brush)
        )

        Button(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(MyDimens.dimens.paddingMedium),
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            )
        ) {
            Text(
                text = stringResource(id = R.string.skip),
                color = Orange_enabled
            )
        }

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 110.dp)
                .padding(MyDimens.dimens.paddingMedium)
        ){
            Text(
                text = stringResource(id = R.string.welcome),
                color = Color.Black,
                style = MaterialTheme.typography.displayLarge
            )

            Text(
                text = stringResource(id = R.string.app_name),
                color = Orange_enabled,
                style = MaterialTheme.typography.headlineLarge
            )

            Text(
                modifier = Modifier
                    .padding(vertical = MyDimens.dimens.paddingNormal),
                text = stringResource(id = R.string.slogan),
                color = Color.DarkGray,
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(MyDimens.dimens.paddingMedium),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            GroupSocialButtons(
                titleInfo = R.string.sign_in_with,
                onFacebookClick = {},
                onGoogleClick = {}
            )

            SpacerHeightMedium()

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(MyDimens.dimens.buttonHeightNormal)
                    .padding(horizontal = MyDimens.dimens.paddingMedium),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Gray.copy(alpha = .5f)
                ),
                shape = MaterialTheme.shapes.extraLarge,
                border = BorderStroke(
                    width = MyDimens.dimens.borderExtraSmall,
                    color = Color.White
                ),
                onClick = onNavigateToSignUp
            ) {
                Text(
                    text = stringResource(id = R.string.sign_in_email),
                    color = Color.White
                )
            }

            SpacerHeightMedium()

            RowQuestionWithTextButton(
                modifier = Modifier
                    .fillMaxWidth(),
                textQuestion = R.string.alread_have_account,
                textOption = R.string.login,
                colorQuestion = Color.White,
                onClickText = {
                    viewModel.onEvent(WelcomeEvent.SignInButtonPressed)
                }
            )
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun WelcomeScreenPreview(){
    WelcomeScreen(
        onNavigateToSignIn = {},
        onNavigateToSignUp = {},
        onNavigateToHome = {}
    )
}