package com.example.appone.data.schedule.mapper

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.appone.data.schedule.model.PrayScheduleResponse
import com.example.appone.domain.schedule.model.PraySchedule
import com.example.appone.util.TimeUtil
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

object PrayScheduleResponseMapper {
    fun Single<PrayScheduleResponse>.toPraySchedules(): Single<List<PraySchedule>> {
        val praySchedules = ArrayList<PraySchedule>()
        val response = this.blockingGet()
        val cityName = response.state ?: ""
        val format = "yyyy-M-dd hh:mm a"
        response.items?.get(0)?.let { dataItem ->
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
        return Single.create{subscriber->
        subscriber.onSuccess(praySchedules) }
    }
}