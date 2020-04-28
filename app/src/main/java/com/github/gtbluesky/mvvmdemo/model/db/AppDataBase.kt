package com.github.gtbluesky.mvvmdemo.model.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.github.gtbluesky.mvvmdemo.base.BaseApplication
import com.github.gtbluesky.mvvmdemo.model.db.dao.HomeDao
import com.github.gtbluesky.mvvmdemo.model.db.dao.ResponseDao
import com.github.gtbluesky.mvvmdemo.model.db.entity.ResponseEntity


@Database(
    entities = [ResponseEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getResponseDao(): ResponseDao
    abstract fun getHomeDao(): HomeDao

    companion object {
        @Volatile
        private var instance: AppDataBase? = null

        fun getInstance(): AppDataBase {
            return instance ?: synchronized(this) {
                instance ?: buildDataBase().also { instance = it }
            }
        }

        private fun buildDataBase(): AppDataBase {
            return Room
                .databaseBuilder(BaseApplication.context, AppDataBase::class.java, "mvvm.db")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}