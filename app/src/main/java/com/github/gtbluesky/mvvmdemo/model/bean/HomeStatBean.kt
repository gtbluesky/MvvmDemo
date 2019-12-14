package com.github.gtbluesky.mvvmdemo.model.bean

data class HomeStatBean(
        val news_total: String,
        val project_total: String,
        val report_total: String,
        val task_total: String
)

data class UnDoTotal(
        val total: Int
)