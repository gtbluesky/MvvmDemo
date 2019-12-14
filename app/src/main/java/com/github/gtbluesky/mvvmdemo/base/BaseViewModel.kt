package com.github.gtbluesky.mvvmdemo.base

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

open class BaseViewModel : ViewModel() {

    val mErrorMsg: MutableLiveData<String> = MutableLiveData()
    val mException: MutableLiveData<Throwable> = MutableLiveData()

    fun launchOnMain(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch {
            try {
                coroutineScope {
                    block()
                }
            } catch (e: Throwable) {
                if (e !is CancellationException) {
                    Log.e("BaseViewModel", e.message ?: "")
                    mException.value = e
                } else {
                    throw e
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}