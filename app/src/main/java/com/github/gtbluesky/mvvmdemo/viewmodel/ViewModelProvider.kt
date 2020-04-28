package com.github.gtbluesky.mvvmdemo.viewmodel

import com.github.gtbluesky.mvvmdemo.model.db.AppDataBase
import com.github.gtbluesky.mvvmdemo.model.repository.HomeRepository
import com.github.gtbluesky.mvvmdemo.viewmodel.factory.HomeViewModelFactory

object ViewModelProvider {

    fun provideHomeViewModel(): HomeViewModelFactory {
        val repository: HomeRepository = HomeRepository.getInstance(AppDataBase.getInstance().getHomeDao())
        return HomeViewModelFactory(repository)
    }
}