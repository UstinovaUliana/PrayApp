package com.example.appone.presentation.home

import android.util.Log
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.example.appone.databinding.FragmentHomeBinding
import com.example.appone.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
/*
import kotlinx.coroutines.launch

 */
import timber.log.Timber
import javax.inject.Inject
import com.example.appone.presentation.home.adapter.PrayAdapter
import io.reactivex.disposables.CompositeDisposable

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    @Inject
    lateinit var prayAdapter:PrayAdapter

    private val homeViewModel: HomeViewModel by viewModels()

    override fun constructViewBinding(): ViewBinding = FragmentHomeBinding.inflate(layoutInflater)

    override fun init(viewBinding: ViewBinding) {
        homeViewModel.getPraySchedule()
        initUi()
        fetchPraySchedules()
    }

    private fun initUi() {
        getViewBinding().prayScheduleRv.run {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = prayAdapter
        }
    }

    private fun fetchPraySchedules() {
        homeViewModel.uiState.observe(this.viewLifecycleOwner
        ) { state ->
            when (state) {
                is HomeViewModel.PrayUiState.Loaded -> onLoaded(state.itemState)
                is HomeViewModel.PrayUiState.Error -> showError(state.message)
                is HomeViewModel.PrayUiState.Loading -> showLoading()
            }
        }
    }

    private fun onLoaded(homeItemUiState: HomeItemUiState) {
        homeItemUiState.run {
            getViewBinding().cityTv.text = city
            prayAdapter.update(schedules)
        }
    }

    private fun showLoading() {
        Timber.d("showLoading")
    }

    private fun showError(message:String) {
        Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()
    }
}