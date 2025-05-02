package com.jmarser.mydelivery.di

import com.jmarser.mydelivery.data.local.repository.SharedRepositoryImpl
import com.jmarser.mydelivery.domain.repository.SharedRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Project: My Delivery
 * File: SharedRepositoryModule
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 02/05/2025
 */


@Module
@InstallIn(SingletonComponent::class)
interface SharedRepositoryModule {

    @Binds
    fun providerSharedRepository(
        repo: SharedRepositoryImpl
    ): SharedRepository
}