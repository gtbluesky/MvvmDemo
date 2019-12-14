package com.github.gtbluesky.mvvmdemo.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.gtbluesky.mvvmdemo.model.repository.HomeRepository
import com.github.gtbluesky.mvvmdemo.viewmodel.HomeViewModel

class HomeViewModelFactory(
        private val repository: HomeRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(repository) as T
    }
}