package com.example.appone.data.schedule.mapper

import com.example.appone.data.database.pray.model.PrayEntity
import com.example.appone.domain.schedule.model.PraySchedule
import io.reactivex.Flowable

object PrayEntityMapper {
    fun Flowable<List<PrayEntity>>.toPraySchedules(): Flowable<List<PraySchedule>> {
        return this.map { it ->
            val praySchedules = ArrayList<PraySchedule>()
            it.forEach {
                praySchedules.add(PraySchedule(it.city, it.name, it.time, it.id))
            }
            praySchedules
        }
    }
}