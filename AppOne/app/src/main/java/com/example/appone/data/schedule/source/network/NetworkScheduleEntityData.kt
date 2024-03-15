package com.example.appone.data.schedule.source.network

import android.util.Log
import com.example.appone.data.schedule.api.PrayApi
import com.example.appone.data.schedule.mapper.PrayScheduleResponseMapper.toPraySchedules
import com.example.appone.data.schedule.source.ScheduleEntityData
import com.example.appone.domain.schedule.model.PraySchedule
import com.example.appone.domain.schedule.model.PrayScheduleRequest
import io.reactivex.Flowable
import io.reactivex.Single
import kotlinx.coroutines.flow.asFlow
import javax.inject.Inject

class NetworkScheduleEntityData @Inject constructor(
    private val prayApi: PrayApi
) : ScheduleEntityData {
    override fun getPraySchedule(prayScheduleRequest: PrayScheduleRequest): Flowable<List<PraySchedule>> {
        return prayApi.getPraySchedule(prayScheduleRequest.city).toPraySchedules()
    }

    override fun addPraySchedules(praySchedules: Flowable<List<PraySchedule>>) {
        Log.d("mytag","addPraySchedules")
        // no op
    }
}