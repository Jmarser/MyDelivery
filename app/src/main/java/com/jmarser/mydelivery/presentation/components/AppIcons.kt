package com.jmarser.mydelivery.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Start
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
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

    val ic_start: ImageVector = Icons.Filled.Star

    val ic_not_favorite: ImageVector = Icons.Filled.FavoriteBorder

    val ic_yes_favorite: ImageVector = Icons.Filled.Favorite

    val ic_arrow_right: ImageVector = Icons.Filled.ArrowForwardIos

    val ic_searchbar: ImageVector = Icons.Rounded.Search

    val ic_clear: ImageVector = Icons.Rounded.Clear

    val ic_back: ImageVector = Icons.Filled.ArrowBackIosNew
}