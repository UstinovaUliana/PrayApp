package com.example.appone.presentation.home

import android.util.Log
import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appone.R
import com.example.appone.data.networking.CoroutineDispatcherProvider
import com.example.appone.domain.schedule.model.PraySchedule
import com.example.appone.domain.schedule.model.PrayScheduleRequest
import com.example.appone.domain.schedule.usecase.GetPraySchedule
import com.example.appone.util.ExceptionParser
import com.example.appone.util.TimeUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.ReplaySubject
/*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

 */
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPraySchedules: GetPraySchedule,
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider
) : ViewModel() {
    private val disposable = CompositeDisposable()
    private val _uiState = MutableLiveData<PrayUiState>(PrayUiState.Loading)
    val uiState: LiveData<PrayUiState> = _uiState
    fun getPraySchedule() {
        _uiState.value = PrayUiState.Loading
        val city = "Jakarta"
        val requestParam = PrayScheduleRequest(city, getTodayDate())
        disposable.add ( getPraySchedules.execute(requestParam)
            .subscribe({
            _uiState.value = PrayUiState.Loaded(HomeItemUiState(city,it))
            },{
                _uiState.value = PrayUiState.Error(it.message?:"")
            })
        )
        Log.d("mytag","disposable = ${disposable.size()}")


        /*

        private fun <T : Any, S : Any> Flowable<PagingData<T>>.collectPagingRequest(
        mappedData: (T) -> S
    ) = map { it.map { data -> mappedData(data) } }.cachedIn(viewModelScope)


        fun getPraySchedule() {
        _uiState.value = PrayUiState.Loading
        viewModelScope.launch(coroutineDispatcherProvider.IO()) {
            try {
                val city = "Jakarta"
                val requestParam = PrayScheduleRequest(city, getTodayDate())
                val result = getPraySchedules.execute(requestParam)
                _uiState.value = PrayUiState.Loaded(HomeItemUiState(city, result))
            } catch (error: java.lang.Exception) {
                _uiState.value = PrayUiState.Error(ExceptionParser.getMessage(error))
            }
        }
    }*/
    }

    private fun getTodayDate() = TimeUtil.getDateFormatted(Date())
    sealed class PrayUiState {
        object Loading : PrayUiState()
        class Loaded(val itemState: HomeItemUiState) : PrayUiState()
        class Error(val message: String) : PrayUiState()
    }
}