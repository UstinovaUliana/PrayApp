package com.example.appone.data.schedule.repository

import com.example.appone.data.schedule.factory.ScheduleFactory
import com.example.appone.domain.schedule.model.PraySchedule
import com.example.appone.domain.schedule.model.PrayScheduleRequest
import com.example.appone.domain.schedule.repository.ScheduleRepository
import com.example.appone.util.Source
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(
    private val scheduleFactory: ScheduleFactory
) :ScheduleRepository {
    override suspend fun getPraySchedules(prayScheduleRequest: PrayScheduleRequest) : List<PraySchedule> {
        return scheduleFactory.create(Source.LOCAL).getPraySchedule(prayScheduleRequest)
            .ifEmpty {syncPraySchedule(prayScheduleRequest)}
    }
    private suspend fun syncPraySchedule(prayScheduleRequest: PrayScheduleRequest): List<PraySchedule> {
        return scheduleFactory.create(Source.NETWORK).getPraySchedule(prayScheduleRequest)
            .also {
                prayScheduleFromNetwork ->
                scheduleFactory.create(Source.LOCAL).addPraySchedules(prayScheduleFromNetwork)
            }
    }
}