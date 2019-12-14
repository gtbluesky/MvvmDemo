package com.github.gtbluesky.mvvmdemo.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.github.gtbluesky.mvvmdemo.model.db.dao.HomeDao
import com.github.gtbluesky.mvvmdemo.model.db.dao.ResponseDao
import com.github.gtbluesky.mvvmdemo.model.db.entity.ResponseEntity


@Database(entities = [ResponseEntity::class],
        version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getResponseDao(): ResponseDao
    abstract fun getHomeDao(): HomeDao

    companion object {
        @Volatile
        private var instance: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            return instance ?: synchronized(this) {
                instance ?: buildDataBase(context).also { instance = it }
            }
        }

        private fun buildDataBase(context: Context): AppDataBase {
            return Room
                .databaseBuilder(context, AppDataBase::class.java, "mvvm.db")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}