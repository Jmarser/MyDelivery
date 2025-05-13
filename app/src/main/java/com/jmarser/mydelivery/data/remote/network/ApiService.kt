package com.jmarser.mydelivery.data.remote.network

import com.jmarser.mydelivery.data.modelsDto.AuthResponse
import com.jmarser.mydelivery.data.modelsDto.CategoryResponse
import com.jmarser.mydelivery.data.modelsDto.RestaurantsResponse
import com.jmarser.mydelivery.data.modelsDto.SignInRequestDto
import com.jmarser.mydelivery.data.modelsDto.SignUpRequestDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Project: My Delivery
 * File: ApiService
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 24/04/2025
 */

interface ApiService {

    @POST("auth/signup")
    suspend fun signUp(@Body request: SignUpRequestDto): Response<AuthResponse>

    @POST("auth/login")
    suspend fun signIn(@Body request: SignInRequestDto): Response<AuthResponse>

    @GET("categories")
    suspend fun getAllCategories(): Response<CategoryResponse>

    @GET("restaurants")
    suspend fun getRestaurants(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): Response<RestaurantsResponse>

    @GET("restaurants")
    suspend fun getRestaurantsByCategory(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("categoryId") categoryId: String
    ): Response<RestaurantsResponse>

}