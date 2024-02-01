package com.example.appone.data.schedule.mapper

import com.example.appone.data.database.pray.model.PrayEntity
import com.example.appone.domain.schedule.model.PraySchedule

object PrayScheduleMapper {

    fun List<PraySchedule>.toPrayEntities(): List<PrayEntity> {
        val prayEntities = ArrayList<PrayEntity>()
        forEach {
            prayEntities.add(PrayEntity(it.id, it.city, it.name, it.time))
        }
        return prayEntities
    }
}