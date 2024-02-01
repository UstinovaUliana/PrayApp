package com.example.appone.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import com.example.appone.presentation.home.adapter.PrayAdapter

@Module
@InstallIn(FragmentComponent::class)
object HomeModule {
    @Provides
    fun providePrayAdapter(): PrayAdapter {
        return PrayAdapter()
    }
}