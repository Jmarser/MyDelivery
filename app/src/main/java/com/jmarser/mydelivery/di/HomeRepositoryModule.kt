package com.jmarser.mydelivery.di

import com.jmarser.mydelivery.data.remote.repository.HomeRepositoryImpl
import com.jmarser.mydelivery.domain.repository.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Project: My Delivery
 * File: HomeRepositoryModule
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 06/05/2025
 */

@Module
@InstallIn(SingletonComponent::class)
interface HomeRepositoryModule {

    @Binds
    fun provideHomeRepository(
        repo: HomeRepositoryImpl
    ): HomeRepository
}