package com.github.gtbluesky.mvvmdemo.model.repository

import com.github.gtbluesky.mvvmdemo.model.api.RetrofitClient
import com.github.gtbluesky.mvvmdemo.model.db.dao.HomeDao

class HomeRepository private constructor(private val dao: HomeDao) {

    companion object {
        @Volatile
        private var instance: HomeRepository? = null

        fun getInstance(dao: HomeDao) =
                instance ?: synchronized(this) {
                    instance ?: HomeRepository(dao).also { instance = it }
                }
    }

    suspend fun getStatInfo(type: Int) = RetrofitClient.request.getStatInfo(type = type)

    suspend fun getPersonalMenu() = RetrofitClient.request.getPersonalMenu()

}