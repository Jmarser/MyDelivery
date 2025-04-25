package com.jmarser.mydelivery.di

import com.jmarser.mydelivery.data.remote.repository.AuthRepositoryImpl
import com.jmarser.mydelivery.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Project: My Delivery
 * File: AuthRepositoryModule
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 25/04/2025
 */

@Module
@InstallIn(SingletonComponent::class)
interface AuthRepositoryModule {

    @Binds
    fun provideAuthRepository(
        authRepo: AuthRepositoryImpl
    ): AuthRepository

}