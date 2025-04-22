package com.jmarser.mydelivery.di

import com.jmarser.mydelivery.core.ValidateFormImpl
import com.jmarser.mydelivery.core.ValidationForm
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object ValidationFormModule {

    @Provides
    fun provideValidationForm(): ValidationForm = ValidateFormImpl()
}