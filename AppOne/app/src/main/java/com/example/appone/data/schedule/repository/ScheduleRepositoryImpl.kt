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
    scheduleFactory: ScheduleFactory
) : ScheduleRepository {

    private val localEntity = scheduleFactory.create(Source.LOCAL)
    private val networkEntity = scheduleFactory.create(Source.NETWORK)

    override fun getPraySchedules(prayScheduleRequest: PrayScheduleRequest): Flowable<List<PraySchedule>> =
        basePraySchedule(prayScheduleRequest)
            .mergeWith(syncPraySchedule(prayScheduleRequest))
            .subscribeOn(Schedulers.io())

    private fun basePraySchedule(prayScheduleRequest: PrayScheduleRequest): Flowable<List<PraySchedule>> {
        return localEntity.getPraySchedule(prayScheduleRequest)
    }

    private fun syncPraySchedule(prayScheduleRequest: PrayScheduleRequest): Flowable<List<PraySchedule>> {
        val prayScheduleFromNetwork =
            networkEntity.getPraySchedule(prayScheduleRequest)
        localEntity.addPraySchedules(prayScheduleFromNetwork)

        return prayScheduleFromNetwork
    }

}