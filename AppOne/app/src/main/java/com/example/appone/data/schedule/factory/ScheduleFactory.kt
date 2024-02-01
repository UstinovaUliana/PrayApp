package com.example.appone.data.schedule.factory

import com.example.appone.data.schedule.source.ScheduleEntityData
import com.example.appone.data.schedule.source.local.LocalScheduleEntityData
import com.example.appone.data.schedule.source.network.NetworkScheduleEntityData
import com.example.appone.util.Source
import javax.inject.Inject

class ScheduleFactory @Inject constructor(
    private val networkScheduleEntityData: NetworkScheduleEntityData,
    private val localScheduleEntityData: LocalScheduleEntityData
) {
    fun create(source: Source): ScheduleEntityData{
        return when (source) {
            Source.NETWORK->networkScheduleEntityData
            else->localScheduleEntityData
        }
    }
}