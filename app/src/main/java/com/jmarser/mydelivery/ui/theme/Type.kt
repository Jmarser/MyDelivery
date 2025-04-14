package com.jmarser.mydelivery.ui.theme

import android.content.res.Configuration
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.jmarser.mydelivery.R


val Poppins = FontFamily(
    Font(R.font.poppins_thin, FontWeight.Thin),
    Font(R.font.poppins_extralight, FontWeight.ExtraLight),
    Font(R.font.poppins_light, FontWeight.Light),
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_medium, FontWeight.Medium),
    Font(R.font.poppins_semibold, FontWeight.SemiBold),
    Font(R.font.poppins_bold, FontWeight.Bold),
    Font(R.font.poppins_extrabold, FontWeight.ExtraBold),
    Font(R.font.poppins_black, FontWeight.Black)
)


@Composable
fun dynamicTypography(): Typography{
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    val screenWithDp = configuration.screenWidthDp
    val isTablet = screenWithDp >= 600


    return Typography(
        displayLarge = TextStyle(
            fontFamily = Poppins,
            fontWeight = FontWeight.Black,
            fontSize = when{
                isTablet -> 59.sp
                isLandscape -> 58.sp
                else -> 57.sp
            }, //57.sp,
            lineHeight = when{
                isTablet -> 76.sp
                isLandscape -> 75.sp
                else -> 74.sp
            }, //64.sp,
            letterSpacing = 0.5.sp
        ),
        displayMedium = TextStyle(
            fontFamily = Poppins,
            fontWeight = FontWeight.Normal,
            fontSize = when{
                isTablet -> 47.sp
                isLandscape -> 46.sp
                else -> 45.sp
            }, //45.sp,
            lineHeight = when{
                isTablet -> 54.sp
                isLandscape -> 53.sp
                else -> 52.sp
            }, //52.sp,
            letterSpacing = 0.5.sp
        ),
        displaySmall = TextStyle(
            fontFamily = Poppins,
            fontWeight = FontWeight.Normal,
            fontSize = when{
                isTablet -> 38.sp
                isLandscape -> 37.sp
                else -> 36.sp
            }, //36.sp,
            lineHeight = when{
                isTablet -> 46.sp
                isLandscape -> 45.sp
                else -> 44.sp
            }, //44.sp,
            letterSpacing = 0.5.sp
        ),
        headlineLarge = TextStyle(
            fontFamily = Poppins,
            fontWeight = FontWeight.Black,
            fontSize = when{
                isTablet -> 34.sp
                isLandscape -> 33.sp
                else -> 32.sp
            }, //32.sp,
            lineHeight = when{
                isTablet -> 42.sp
                isLandscape -> 41.sp
                else -> 40.sp
            }, //40.sp,
            letterSpacing = 0.5.sp
        ),
        headlineMedium = TextStyle(
            fontFamily = Poppins,
            fontWeight = FontWeight.Normal,
            fontSize = when{
                isTablet -> 30.sp
                isLandscape -> 29.sp
                else -> 28.sp
            }, //28.sp,
            lineHeight = when{
                isTablet -> 38.sp
                isLandscape -> 37.sp
                else -> 36.sp
            }, //36.sp,
            letterSpacing = 0.5.sp
        ),
        headlineSmall = TextStyle(
            fontFamily = Poppins,
            fontWeight = FontWeight.Normal,
            fontSize = when{
                isTablet -> 26.sp
                isLandscape -> 25.sp
                else -> 24.sp
            }, //24.sp,
            lineHeight = when{
                isTablet -> 34.sp
                isLandscape -> 33.sp
                else -> 32.sp
            }, //32.sp,
            letterSpacing = 0.5.sp
        ),
        titleLarge = TextStyle(
            fontFamily = Poppins,
            fontWeight = FontWeight.ExtraBold,
            fontSize = when{
                isTablet -> 24.sp
                isLandscape -> 23.sp
                else -> 22.sp
            }, //22.sp,
            lineHeight = when{
                isTablet -> 30.sp
                isLandscape -> 29.sp
                else -> 28.sp
            }, //28.sp,
            letterSpacing = 0.sp
        ),
        titleMedium = TextStyle(
            fontFamily = Poppins,
            fontWeight = FontWeight.Normal,
            fontSize = when{
                isTablet -> 18.sp
                isLandscape -> 17.sp
                else -> 16.sp
            }, //16.sp,
            lineHeight = when{
                isTablet -> 26.sp
                isLandscape -> 25.sp
                else -> 24.sp
            }, //24.sp,
            letterSpacing = 0.sp
        ),
        titleSmall = TextStyle(
            fontFamily = Poppins,
            fontWeight = FontWeight.Normal,
            fontSize = when{
                isTablet -> 16.sp
                isLandscape -> 15.sp
                else -> 14.sp
            }, //14.sp,
            lineHeight = when{
                isTablet -> 22.sp
                isLandscape -> 21.sp
                else -> 20.sp
            }, //20.sp,
            letterSpacing = 0.sp
        ),
        bodyLarge = TextStyle(
            fontFamily = Poppins,
            fontWeight = FontWeight.Normal,
            fontSize = when{
                isTablet -> 18.sp
                isLandscape -> 17.sp
                else -> 16.sp
            }, //16.sp,
            lineHeight = when{
                isTablet -> 26.sp
                isLandscape -> 25.sp
                else -> 24.sp
            }, //24.sp,
            letterSpacing = 0.5.sp
        ),
        bodyMedium = TextStyle(
            fontFamily = Poppins,
            fontWeight = FontWeight.Normal,
            fontSize = when{
                isTablet -> 16.sp
                isLandscape -> 15.sp
                else -> 14.sp
            }, //14.sp,
            lineHeight = when{
                isTablet -> 22.sp
                isLandscape -> 21.sp
                else -> 20.sp
            }, //20.sp,
            letterSpacing = 0.5.sp
        ),
        bodySmall = TextStyle(
            fontFamily = Poppins,
            fontWeight = FontWeight.Normal,
            fontSize = when{
                isTablet -> 14.sp
                isLandscape -> 13.sp
                else -> 12.sp
            }, //12.sp,
            lineHeight = when{
                isTablet -> 18.sp
                isLandscape -> 17.sp
                else -> 16.sp
            }, //16.sp,
            letterSpacing = 0.5.sp
        ),
        labelLarge = TextStyle(
            fontFamily = Poppins,
            fontWeight = FontWeight.Medium,
            fontSize = when{
                isTablet -> 16.sp
                isLandscape -> 15.sp
                else -> 14.sp
            }, //14.sp,
            lineHeight = when{
                isTablet -> 22.sp
                isLandscape -> 21.sp
                else -> 20.sp
            }, //20.sp,
            letterSpacing = 0.5.sp
        ),
        labelMedium = TextStyle(
            fontFamily = Poppins,
            fontWeight = FontWeight.Normal,
            fontSize = when{
                isTablet -> 14.sp
                isLandscape -> 13.sp
                else -> 12.sp
            }, //12.sp,
            lineHeight = when{
                isTablet -> 18.sp
                isLandscape -> 17.sp
                else -> 16.sp
            }, //16.sp,
            letterSpacing = 0.5.sp
        ),
        labelSmall = TextStyle(
            fontFamily = Poppins,
            fontWeight = FontWeight.Medium,
            fontSize = when{
                isTablet -> 13.sp
                isLandscape -> 12.sp
                else -> 11.sp
            }, //11.sp,
            lineHeight = when{
                isTablet -> 18.sp
                isLandscape -> 17.sp
                else -> 16.sp
            }, //16.sp,
            letterSpacing = 0.5.sp
        )
    )
}

/*

// Set of Material typography styles to start with
val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = 0.5.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    labelLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.5.sp
    ),
)*/
