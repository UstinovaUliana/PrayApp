package com.example.appone.data.schedule.source.network

import com.example.appone.data.schedule.api.PrayApi
import com.example.appone.data.schedule.mapper.PrayScheduleResponseMapper.toPraySchedules
import com.example.appone.data.schedule.source.ScheduleEntityData
import com.example.appone.domain.schedule.model.PraySchedule
import com.example.appone.domain.schedule.model.PrayScheduleRequest
import javax.inject.Inject

class NetworkScheduleEntityData @Inject constructor(
    private val prayApi: PrayApi
) : ScheduleEntityData {
    override suspend fun getPraySchedule(prayScheduleRequest: PrayScheduleRequest): List<PraySchedule> {
        return prayApi.getPraySchedule(prayScheduleRequest.city).toPraySchedules()
    }

    override suspend fun addPraySchedules(praySchedules: List<PraySchedule>) {
        // no op
    }
}