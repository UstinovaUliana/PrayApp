package com.example.appone.data.schedule.source

import com.example.appone.domain.schedule.model.PraySchedule
import com.example.appone.domain.schedule.model.PrayScheduleRequest
import io.reactivex.Flowable
import io.reactivex.Single

interface ScheduleEntityData {
    fun getPraySchedule(prayScheduleRequest: PrayScheduleRequest): Flowable<List<PraySchedule>>
    fun addPraySchedules(praySchedules: Flowable<List<PraySchedule>>)
}