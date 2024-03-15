package com.example.appone.data.schedule.api

import androidx.lifecycle.LiveData
import com.example.appone.BuildConfig
import com.example.appone.data.schedule.model.PrayScheduleResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PrayApi {

    @GET("/jakarta/daily.json")
    fun getPraySchedule(
        @Query("city") city: String,
        @Query("key") key: String? = BuildConfig.API_KEY
    ): Single<PrayScheduleResponse>
}