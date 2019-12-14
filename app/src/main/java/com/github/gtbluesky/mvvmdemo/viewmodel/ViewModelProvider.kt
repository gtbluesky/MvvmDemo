package com.github.gtbluesky.mvvmdemo.viewmodel

import android.content.Context
import com.github.gtbluesky.mvvmdemo.model.db.AppDataBase
import com.github.gtbluesky.mvvmdemo.model.repository.HomeRepository
import com.github.gtbluesky.mvvmdemo.viewmodel.factory.HomeViewModelFactory

object ViewModelProvider {

    fun provideHomeViewModel(context: Context): HomeViewModelFactory {
        val repository: HomeRepository = HomeRepository.getInstance(AppDataBase.getInstance(context).getHomeDao())
        return HomeViewModelFactory(repository)
    }
}