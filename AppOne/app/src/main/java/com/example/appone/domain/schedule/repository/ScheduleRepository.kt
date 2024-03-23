package com.example.appone.domain.schedule.repository

import com.example.appone.domain.schedule.model.PraySchedule
import com.example.appone.domain.schedule.model.PrayScheduleRequest
import io.reactivex.Flowable

interface ScheduleRepository {
    fun getPraySchedules(prayScheduleRequest: PrayScheduleRequest): Flowable<List<PraySchedule>>
}