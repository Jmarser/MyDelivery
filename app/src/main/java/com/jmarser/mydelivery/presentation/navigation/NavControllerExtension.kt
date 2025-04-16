package com.jmarser.mydelivery.presentation.navigation

import androidx.navigation.NavHostController

/**
 * Project: My Delivery
 * File: NavControllerExtension
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 16/04/2025
 */


/**
 * Función de extensión que navega hacia la pantalla de inicio de sesión (SignIn)
 *
 * Elimina la pantalla de bienvenida (Welcome) del back stack de navegación para evitar
 * que el usuario pueda regresar a ella.
 *
 * */
fun NavHostController.navigateToSignIn(){
    this.navigate(AppRoutes.SignIn){
        popUpTo(AppRoutes.Welcome){
            inclusive = true
        }
    }
}

/**
 * Función de extensión que navega hacia la pantalla de registro (SignUp)
 *
 * Elimina la pantalla de bienvenida (Welcome) del back stack de navegación para evitar
 * que el usuario pueda regresar a ella.
 *
 * */
fun NavHostController.navigateToSignUp(){
    this.navigate(AppRoutes.SignUp){
        popUpTo(AppRoutes.Welcome){
            inclusive = true
        }
    }
}

/**
 * Función de extensión que navega hacia la pantalla principal (Home) eliminando el historial de navegación hasta
 * una ruta específica.
 *
 * @param from Ruta desde la cual se debe eliminar el back stack. La ruta especificada también se elimina.
 *
 * Esta variante permite borrar parte del historial, sólo hasta la pantalla desde la que se inicia la
 * navegación.
 *
 * Además, evita múltiples instancias de Home con `launchSingleTop = true`.
 * */
fun NavHostController.navigateToHome(from: Any){
    this.navigate(AppRoutes.Home){
        popUpTo(from){
            inclusive = true
        }
        launchSingleTop = true
    }
}

/**
 * Función de extensión que navega hacia la pantalla principal (Home) eliminando completamente el back stack de navegación.
 *
 * Esta función borra completamente el historial de navegación, evitando que el usuario no pueda regresar
 * a las pantallas de SignIn ni SignUp.
 *
 * Además, evita múltiples instancias de Home con `launchSingleTop = true`.
 *
 * */
fun NavHostController.navigateToHome(){
    this.navigate(AppRoutes.Home){
        popUpTo(0)
        launchSingleTop = true
    }
}