package com.github.gtbluesky.mvvmdemo.model.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "response")
data class ResponseEntity(
        @ColumnInfo(name = "url") val url: String,
        @ColumnInfo(name = "error_code") val error_code: String,
        @ColumnInfo(name = "error_message") val error_message: String,
        @ColumnInfo(name = "data") val data: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id = 0L
}