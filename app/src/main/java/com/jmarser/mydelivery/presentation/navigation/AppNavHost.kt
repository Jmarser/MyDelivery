package com.jmarser.mydelivery.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.jmarser.mydelivery.presentation.Greeting
import com.jmarser.mydelivery.presentation.feature_auth.sign_in.SignInScreen
import com.jmarser.mydelivery.presentation.feature_auth.sign_up.SignUpScreen
import com.jmarser.mydelivery.presentation.feature_auth.welcome.WelcomeScreen
import com.jmarser.mydelivery.presentation.feature_home.HomeScreen
import com.jmarser.mydelivery.presentation.feature_restaurantDetails.RestaurantDetailsScreen

/**
 * Project: My Delivery
 * File: AppNavHost
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 16/04/2025
 */

@Composable
fun AppNavHost(
    navController: NavHostController
) {
    val ANIMATION_DURATION = 400

    NavHost(
        navController = navController,
        startDestination = AppRoutes.Welcome,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(ANIMATION_DURATION)
            ) + fadeIn(animationSpec = tween(ANIMATION_DURATION))
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(ANIMATION_DURATION)
            ) + fadeOut(animationSpec = tween(ANIMATION_DURATION))
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(ANIMATION_DURATION)
            ) + fadeIn(animationSpec = tween(ANIMATION_DURATION))
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(ANIMATION_DURATION)
            ) + fadeOut(animationSpec = tween(ANIMATION_DURATION))
        }
    ) {
        composable<AppRoutes.Welcome> {
            WelcomeScreen(
                onNavigateToSignIn = {
                    navController.navigateToSignIn()
                },
                onNavigateToSignUp = {
                    navController.navigateToSignUp()
                },
                onNavigateToHome = {
                    navController.navigateToHome()
                }
            )
        }

        composable<AppRoutes.SignIn> {
            SignInScreen(
                onNavigateToSignUp = {
                    navController.navigate(AppRoutes.SignUp)
                },
                onNavigateToHome = {
                    navController.navigateToHome()
                }
            )
        }

        composable<AppRoutes.SignUp> {
            SignUpScreen(
                onNavigateToSignIn = {
                    navController.navigate(AppRoutes.SignIn)
                },
                onNavigateToHome = {
                    navController.navigateToHome()
                }
            )
        }

        composable<AppRoutes.Home> {
            HomeScreen(
                onNavigateToRestaurantDetails = { restaurantId ->
                    navController.navigate(AppRoutes.RestaurantDetails(restaurantId))
                }
            )
        }

        composable<AppRoutes.RestaurantDetails> {
            val args = it.toRoute<AppRoutes.RestaurantDetails>()
            RestaurantDetailsScreen(
                restaurantId = args.restaurantId,
                onNavigateToBack = {
                    navController.navigateToBack()
                }
            )
        }
    }
}