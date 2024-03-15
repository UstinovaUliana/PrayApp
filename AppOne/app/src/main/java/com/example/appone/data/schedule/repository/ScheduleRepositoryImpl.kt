package com.example.appone.data.schedule.repository

import com.example.appone.data.schedule.factory.ScheduleFactory
import com.example.appone.domain.schedule.model.PraySchedule
import com.example.appone.domain.schedule.model.PrayScheduleRequest
import com.example.appone.domain.schedule.repository.ScheduleRepository
import com.example.appone.util.Source
import io.reactivex.Single
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(
    private val scheduleFactory: ScheduleFactory
) : ScheduleRepository {
    override fun getPraySchedules(prayScheduleRequest: PrayScheduleRequest): Single<List<PraySchedule>> {
        var single = scheduleFactory.create(Source.LOCAL).getPraySchedule(prayScheduleRequest)

        if(single.blockingGet().isEmpty()) { single=syncPraySchedule(prayScheduleRequest) }

        return single
    }

    private fun syncPraySchedule(prayScheduleRequest: PrayScheduleRequest): Single<List<PraySchedule>> {

        return scheduleFactory.create(Source.NETWORK).getPraySchedule(prayScheduleRequest)
            .also { prayScheduleFromNetwork ->
                scheduleFactory.create(Source.LOCAL).addPraySchedules(prayScheduleFromNetwork.blockingGet())
            }

    }

}