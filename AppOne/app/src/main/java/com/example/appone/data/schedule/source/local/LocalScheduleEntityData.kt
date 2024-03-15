package com.example.appone.data.schedule.source.local

import com.example.appone.data.database.pray.dao.PrayDao
import com.example.appone.data.schedule.mapper.PrayEntityMapper.toPraySchedules
import com.example.appone.data.schedule.mapper.PrayScheduleMapper.toPrayEntities
import com.example.appone.data.schedule.source.ScheduleEntityData
import com.example.appone.domain.schedule.model.PraySchedule
import com.example.appone.domain.schedule.model.PrayScheduleRequest
import com.example.appone.util.TimeUtil
import io.reactivex.Single
import javax.inject.Inject

class LocalScheduleEntityData @Inject constructor(
    private val prayDao: PrayDao
): ScheduleEntityData{
    override fun getPraySchedule(prayScheduleRequest: PrayScheduleRequest): Single<List<PraySchedule>> {
        val from = TimeUtil.getTimestamp(prayScheduleRequest.date)
        val to = TimeUtil.getTimestamp(prayScheduleRequest.date, 1)

        return prayDao.getPraySchedule(prayScheduleRequest.city, from, to).toPraySchedules()
    }
    override fun addPraySchedules(praySchedules: List<PraySchedule>) {
        val prayEntities = praySchedules.toPrayEntities()
        prayDao.insert(prayEntities)
    }
}