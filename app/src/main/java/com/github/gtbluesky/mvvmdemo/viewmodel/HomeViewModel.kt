package com.github.gtbluesky.mvvmdemo.viewmodel

import androidx.lifecycle.MutableLiveData
import com.github.gtbluesky.mvvmdemo.base.BaseViewModel
import com.github.gtbluesky.mvvmdemo.model.bean.HomeMenuBean
import com.github.gtbluesky.mvvmdemo.model.bean.HomeStatBean
import com.github.gtbluesky.mvvmdemo.model.repository.HomeRepository
import com.github.gtbluesky.mvvmdemo.util.handleResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class HomeViewModel(private val repository: HomeRepository) : BaseViewModel() {

    val statValue = MutableLiveData<HomeStatBean>()
    val menuValue = MutableLiveData<List<HomeMenuBean>>()

    fun loadStateInfo() {
        launchOnMain {
            val statInfoResult = async(Dispatchers.IO) {
                repository.getStatInfo(4)
            }

            val menuResult = async(Dispatchers.IO) {
                repository.getPersonalMenu()
            }

            handleResponse<HomeStatBean>(
                statInfoResult.await(),
                {
                    statValue.value = it
                },
                {
                    errorMsg.value = it
                }
            )
            handleResponse<List<HomeMenuBean>>(
                menuResult.await(),
                {
                    menuValue.value = it
                },
                {
                    errorMsg.value = it
                }
            )
        }
    }

    fun loadSurveyData(type: Int) {
        launchOnMain {
            val statInfoResult = withContext(Dispatchers.IO) {
                repository.getStatInfo(4)
            }
            handleResponse<HomeStatBean>(
                statInfoResult,
                {
                    statValue.value = it
                },
                {
                    errorMsg.value = it
                }
            )
        }
    }
}