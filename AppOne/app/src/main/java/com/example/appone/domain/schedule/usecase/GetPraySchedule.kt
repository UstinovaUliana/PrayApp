package com.example.appone.domain.schedule.usecase

import com.example.appone.domain.schedule.model.PraySchedule
import com.example.appone.domain.schedule.model.PrayScheduleRequest
import com.example.appone.domain.schedule.repository.ScheduleRepository
import javax.inject.Inject

class GetPraySchedule @Inject constructor(
    private val scheduleRepository: ScheduleRepository
) {
    suspend fun execute(request: PrayScheduleRequest): List<PraySchedule> {
        return scheduleRepository.getPraySchedules(request)
    }
}