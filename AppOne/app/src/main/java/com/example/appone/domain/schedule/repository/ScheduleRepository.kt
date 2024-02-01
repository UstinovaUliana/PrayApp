package com.example.appone.domain.schedule.repository

import com.example.appone.domain.schedule.model.PraySchedule
import com.example.appone.domain.schedule.model.PrayScheduleRequest

interface ScheduleRepository {
    suspend fun getPraySchedules(prayScheduleRequest: PrayScheduleRequest): List<PraySchedule>
}