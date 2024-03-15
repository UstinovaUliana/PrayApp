package com.example.appone.data.schedule.mapper

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.appone.data.database.pray.model.PrayEntity
import com.example.appone.domain.schedule.model.PraySchedule
import io.reactivex.Single

object PrayEntityMapper {
    fun LiveData<List<PrayEntity>>.toPraySchedules(): Single<List<PraySchedule>> {
        val praySchedules = ArrayList<PraySchedule>()

        this.value?.forEach {
            praySchedules.add(PraySchedule(it.city, it.name, it.time, it.id))
        }
        return Single.create{subscriber->
            subscriber.onSuccess(praySchedules) }
    }
}