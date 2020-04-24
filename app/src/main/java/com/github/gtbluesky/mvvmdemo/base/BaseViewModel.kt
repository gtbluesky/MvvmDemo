package com.github.gtbluesky.mvvmdemo.base

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

open class BaseViewModel : ViewModel() {

    val errorMsg: MutableLiveData<String> = MutableLiveData()
    val exception: MutableLiveData<Throwable> = MutableLiveData()

    fun launchOnMain(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch {
            try {
                coroutineScope {
                    block()
                }
            } catch (e: Throwable) {
                if (e !is CancellationException) {
                    Log.e("BaseViewModel", e.message ?: "")
                    exception.value = e
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