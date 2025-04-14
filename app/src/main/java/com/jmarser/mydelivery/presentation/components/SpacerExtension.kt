package com.jmarser.mydelivery.presentation.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jmarser.mydelivery.ui.theme.MyDimens

/**
 * Project: My Delivery
 * File: SpacerExtension
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 10/04/2025
 */

/**
 * Altura de 4dp
 * */
@Composable
fun SpacerHeightSmall(){
    Spacer(modifier = Modifier.height(MyDimens.dimens.spacerSmall))
}

/**
 * Altura de 8dp
 * */
@Composable
fun SpacerHeightNormal(){
    Spacer(modifier = Modifier.height(MyDimens.dimens.spacerNormal))
}

/**
 * Altura de 16dp
 * */
@Composable
fun SpacerHeightMedium(){
    Spacer(modifier = Modifier.height(MyDimens.dimens.spacerMedium))
}

/**
 * Altura de 24dp
 * */
@Composable
fun SpacerHeightLarge(){
    Spacer(modifier = Modifier.height(MyDimens.dimens.spacerLarge))
}

/**
 * Altura de 40dp
 * */
@Composable
fun SpacerHeightXXL(){
    Spacer(modifier = Modifier.height(MyDimens.dimens.spacerXXL))
}

/**
 * Espacio de 4dp
 * */
@Composable
fun SpacerWidthSmall(){
    Spacer(modifier = Modifier.width(MyDimens.dimens.spacerSmall))
}

/**
 * Espacio de 8dp
 * */
@Composable
fun SpacerWidthNormal(){
    Spacer(modifier = Modifier.width(MyDimens.dimens.spacerNormal))
}

/**
 * Espacio de 16dp
 * */
@Composable
fun SpacerWidthMedium(){
    Spacer(modifier = Modifier.width(MyDimens.dimens.spacerMedium))
}

/**
 * Espacio de 24dp
 * */
@Composable
fun SpacerWidthLarge(){
    Spacer(modifier = Modifier.width(MyDimens.dimens.spacerLarge))
}

/**
 * Espacio de 40dp
 * */
@Composable
fun SpacerWidthXXL(){
    Spacer(modifier = Modifier.width(MyDimens.dimens.spacerXXL))
}