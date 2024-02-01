package com.example.appone.presentation.home

import com.example.appone.domain.schedule.model.PraySchedule

data class HomeItemUiState (
    val city:String,
    val schedules: List<PraySchedule>
)