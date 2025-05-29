package com.jmarser.mydelivery.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.CloudOff
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.AddShoppingCart
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.RemoveCircle
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

    val ic_empty: ImageVector = Icons.Default.SearchOff

    val ic_not_network: ImageVector = Icons.Default.WifiOff

    val ic_error_server: ImageVector = Icons.Default.CloudOff

    val ic_retry: ImageVector = Icons.Default.Replay

    val ic_add: ImageVector = Icons.Rounded.AddCircle

    val ic_minus: ImageVector = Icons.Rounded.RemoveCircle

    val ic_cart: ImageVector = Icons.Rounded.AddShoppingCart
}