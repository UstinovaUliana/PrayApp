package com.example.appone.data.schedule.repository

import com.example.appone.data.schedule.factory.ScheduleFactory
import com.example.appone.domain.schedule.model.PraySchedule
import com.example.appone.domain.schedule.model.PrayScheduleRequest
import com.example.appone.domain.schedule.repository.ScheduleRepository
import com.example.appone.util.Source
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(
    private val scheduleFactory: ScheduleFactory
) : ScheduleRepository {
    override fun getPraySchedules(prayScheduleRequest: PrayScheduleRequest): Flowable<List<PraySchedule>> =
        syncPraySchedule(prayScheduleRequest)
            .startWith(basePraySchedule(prayScheduleRequest))
            .subscribeOn(Schedulers.io())

    private fun basePraySchedule(prayScheduleRequest: PrayScheduleRequest): Flowable<List<PraySchedule>> {
        return scheduleFactory.create(Source.LOCAL).getPraySchedule(prayScheduleRequest)
    }

    private fun syncPraySchedule(prayScheduleRequest: PrayScheduleRequest): Flowable<List<PraySchedule>> {
        val prayScheduleFromNetwork =
            scheduleFactory.create(Source.NETWORK).getPraySchedule(prayScheduleRequest)
        scheduleFactory.create(Source.LOCAL).addPraySchedules(prayScheduleFromNetwork)

        return prayScheduleFromNetwork
    }

}