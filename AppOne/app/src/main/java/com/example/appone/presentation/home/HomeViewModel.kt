package com.example.appone.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appone.domain.schedule.model.PrayScheduleRequest
import com.example.appone.domain.schedule.usecase.GetPraySchedule
import com.example.appone.util.TimeUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPraySchedules: GetPraySchedule
) : ViewModel() {
    private val disposable = CompositeDisposable()
    private val _uiState = MutableLiveData<PrayUiState>(PrayUiState.Loading)
    val uiState: LiveData<PrayUiState> = _uiState
    fun getPraySchedule() {
        _uiState.value = PrayUiState.Loading
        val city = "Jakarta"
        val requestParam = PrayScheduleRequest(city, getTodayDate())
        disposable.add ( getPraySchedules.execute(requestParam)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
            _uiState.value = PrayUiState.Loaded(HomeItemUiState(city,it))
            },{
                _uiState.value = PrayUiState.Error(it.message?:"")
            })
        )
    }

    private fun getTodayDate() = TimeUtil.getDateFormatted(Date())
    sealed class PrayUiState {
        object Loading : PrayUiState()
        class Loaded(val itemState: HomeItemUiState) : PrayUiState()
        class Error(val message: String) : PrayUiState()
    }
}