package com.github.gtbluesky.mvvmdemo.model.db.dao

import androidx.room.*
import com.github.gtbluesky.mvvmdemo.model.db.entity.ResponseEntity

@Dao
interface ResponseDao {

    @Query("SELECT * FROM response WHERE url = :url")
    suspend fun find(url: String = ""): ResponseEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(responseEntity: ResponseEntity):Long

    @Query("DELETE FROM response WHERE url = :url")
    suspend fun delete(url: String)
}