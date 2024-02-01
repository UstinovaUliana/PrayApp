package com.example.appone.data.schedule.mapper

import com.example.appone.data.database.pray.model.PrayEntity
import com.example.appone.domain.schedule.model.PraySchedule

object PrayEntityMapper {
    fun List<PrayEntity>.toPraySchedules(): List<PraySchedule> {
        val praySchedules = ArrayList<PraySchedule>()
        forEach {
            praySchedules.add(PraySchedule(it.city, it.name, it.time, it.id))
        }
        return praySchedules
    }
}