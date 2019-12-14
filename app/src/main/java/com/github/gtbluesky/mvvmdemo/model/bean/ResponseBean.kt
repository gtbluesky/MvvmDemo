package com.github.gtbluesky.mvvmdemo.model.bean

data class ResponseBean<T>(
        val code: Int,
        val msg: String,
        val data: T
)