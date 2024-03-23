package com.example.appone.data.schedule.mapper

import com.example.appone.data.schedule.model.PrayScheduleResponse
import com.example.appone.domain.schedule.model.PraySchedule
import com.example.appone.util.TimeUtil
import io.reactivex.Flowable

object PrayScheduleResponseMapper {
    fun Flowable<PrayScheduleResponse>.toPraySchedules(): Flowable<List<PraySchedule>> {

        return this.map{
            val praySchedules = ArrayList<PraySchedule>()
            val cityName = it.state ?: ""
            val format = "yyyy-M-dd hh:mm a"
            it.items?.get(0)?.let { dataItem ->
                val dateStr = dataItem.date_for
                praySchedules.add(
                    PraySchedule(
                        cityName, "Fajr", TimeUtil.getTimestamp(
                            format, dateStr.plus(" ").plus(dataItem.fajr)
                        )
                    )
                )
                praySchedules.add(
                    PraySchedule(
                        cityName, "Dhuhr", TimeUtil.getTimestamp(
                            format, dateStr.plus(" ").plus(dataItem.dhuhr)
                        )
                    )
                )
                praySchedules.add(
                    PraySchedule(
                        cityName, "Asr", TimeUtil.getTimestamp(
                            format, dateStr.plus(" ").plus(dataItem.asr)
                        )
                    )
                )
                praySchedules.add(
                    PraySchedule(
                        cityName, "Maghrib", TimeUtil.getTimestamp(
                            format, dateStr.plus(" ").plus(dataItem.maghrib)
                        )
                    )
                )
                praySchedules.add(
                    PraySchedule(
                        cityName, "Isha", TimeUtil.getTimestamp(
                            format, dateStr.plus(" ").plus(dataItem.isha)
                        )
                    )
                )
            }
            praySchedules
        }
    }
}