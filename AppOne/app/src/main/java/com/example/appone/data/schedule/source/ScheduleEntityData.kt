package com.example.appone.data.schedule.source

import com.example.appone.domain.schedule.model.PraySchedule
import com.example.appone.domain.schedule.model.PrayScheduleRequest
import io.reactivex.Single

interface ScheduleEntityData {
    fun getPraySchedule(prayScheduleRequest: PrayScheduleRequest): Single<List<PraySchedule>>
    fun addPraySchedules(praySchedules: List<PraySchedule>)
}