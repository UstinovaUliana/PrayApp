package com.example.appone.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.example.appone.data.schedule.api.PrayApi
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    fun providePrayApi(retrofit: Retrofit): PrayApi = retrofit.create(PrayApi::class.java)
}