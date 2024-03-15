package com.example.appone.domain.schedule.usecase

import android.util.Log
import com.example.appone.domain.schedule.model.PraySchedule
import com.example.appone.domain.schedule.model.PrayScheduleRequest
import com.example.appone.domain.schedule.repository.ScheduleRepository
import io.reactivex.Single
import javax.inject.Inject

class GetPraySchedule @Inject constructor(
    private val scheduleRepository: ScheduleRepository
) {
    fun execute(request: PrayScheduleRequest): Single<List<PraySchedule>> {
        return scheduleRepository.getPraySchedules(request)
    }
}