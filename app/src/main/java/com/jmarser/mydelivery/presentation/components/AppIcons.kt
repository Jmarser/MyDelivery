package com.jmarser.mydelivery.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Password
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import com.jmarser.mydelivery.R

object AppIcons {

    @Composable
    fun facebook(): Painter = painterResource(id = R.drawable.ic_facebook)

    @Composable
    fun google(): Painter = painterResource(id = R.drawable.ic_google)

    val ic_user: ImageVector
        @Composable get() = ImageVector.vectorResource(id = R.drawable.ic_user)

    val ic_info: ImageVector
        @Composable get() = ImageVector.vectorResource(id = R.drawable.ic_info)

    val ic_email: ImageVector
        @Composable get() = ImageVector.vectorResource(id = R.drawable.ic_email)

    val ic_eyeOpen: ImageVector
        @Composable get() = ImageVector.vectorResource(id = R.drawable.ic_eye_open)

    val ic_eyeClosed: ImageVector
        @Composable get() = ImageVector.vectorResource(id = R.drawable.ic_eye_closed)

    val ic_password: ImageVector = Icons.Filled.Password
}