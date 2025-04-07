package com.jmarser.mydelivery.ui.theme

import androidx.compose.foundation.shape.CornerSize
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimens(
    val borderExtraSmall: Dp = 1.dp,
    val borderSmall: Dp = 2.dp,
    val borderNormal: Dp = 4.dp,
    val buttonHeightNormal: Dp = 56.dp,
    val iconSizeSmall: Dp = 24.dp,
    val iconSizeNormal: Dp = 36.dp,
    val iconSizeLarge: Dp = 48.dp,
    val paddingSmall: Dp = 4.dp,
    val paddingNormal: Dp = 8.dp,
    val paddingMedium: Dp = 16.dp,
    val paddingLarge: Dp = 24.dp,
    val spacerSmall: Dp = 4.dp,
    val spacerNormal: Dp = 8.dp,
    val spacerMedium: Dp = 16.dp,
    val spacerLarge: Dp = 24.dp,
    val spacerXXL: Dp = 40.dp,
    val roundedShapeSmall: Dp = 4.dp,
    val roundedShapeNormal: Dp = 8.dp,
    val roundedShapeLarge: Dp = 16.dp,
    val roundedShapeXXL: Dp = 24.dp,
    val roundedShapePercent50: CornerSize = CornerSize(50),
    val roundedShapePercent25: CornerSize = CornerSize(25),
    val cardElevationSmall: Dp = 4.dp,
    val cardElevationNormal: Dp = 8.dp,
    val cardElevationLarge: Dp = 12.dp,
)

val DefaultDimens = Dimens()

val TabletDimens = Dimens(
    buttonHeightNormal = 64.dp,
    iconSizeSmall = 36.dp,
    iconSizeNormal = 48.dp,
    iconSizeLarge = 60.dp,
    paddingSmall = 8.dp,
    paddingNormal = 16.dp,
    paddingMedium = 24.dp,
    spacerSmall = 8.dp,
    spacerNormal = 16.dp,
    spacerMedium = 24.dp,
    spacerLarge = 32.dp,
    spacerXXL = 56.dp,
    roundedShapeSmall = 8.dp,
    roundedShapeNormal = 16.dp,
    roundedShapeLarge = 24.dp,
    roundedShapeXXL = 32.dp,
)
