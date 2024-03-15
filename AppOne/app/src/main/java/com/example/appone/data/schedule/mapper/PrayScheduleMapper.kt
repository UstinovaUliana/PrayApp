package com.example.appone.data.schedule.mapper

import com.example.appone.data.database.pray.model.PrayEntity
import com.example.appone.data.schedule.mapper.PrayEntityMapper.toPraySchedules
import com.example.appone.domain.schedule.model.PraySchedule
import io.reactivex.Flowable
import io.reactivex.Single

object PrayScheduleMapper {

    fun Flowable<List<PraySchedule>>.toPrayEntities(): Flowable<List<PrayEntity>> {

        return this.map { it ->
            val prayEntities = ArrayList<PrayEntity>()
            it.forEach {
                prayEntities.add(PrayEntity(it.id, it.city, it.name, it.time))
            }
            prayEntities
        }
    }
}