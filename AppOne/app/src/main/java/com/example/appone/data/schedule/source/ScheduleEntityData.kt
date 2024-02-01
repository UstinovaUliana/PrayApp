package com.example.appone.data.schedule.source

import com.example.appone.domain.schedule.model.PraySchedule
import com.example.appone.domain.schedule.model.PrayScheduleRequest

interface ScheduleEntityData {
    suspend fun getPraySchedule(prayScheduleRequest: PrayScheduleRequest): List<PraySchedule>
    suspend fun addPraySchedules(praySchedules: List<PraySchedule>)
}