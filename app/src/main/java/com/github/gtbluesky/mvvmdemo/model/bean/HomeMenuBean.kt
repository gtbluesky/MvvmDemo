package com.github.gtbluesky.mvvmdemo.model.bean

data class HomeMenuBean(
        val bundle_name: String = "",
        val click_icon: String = "",
        val corporate_id: String = "",
        val create_time: String = "",
        val detail: String = "",
        val fee: String = "",
        val group_id: String = "",
        val icon: Icon? = null,
        val id: String = "",
        val is_common: String = "",
        val is_open: String = "",
        val is_tabbar: String = "",
        val isweb: String = "",
        val name: String,
        val order_id: String = "",
        val outlink: String,
        val sysid: String = "",
        val unclick_icon: String = "",
        val user_id: String = ""
)

data class Icon(
        val dir: String,
        val filename: String,
        val host: String,
        val protocol: String,
        val size: Int,
        val uri: String
) {
    fun getIconUrl() = host + (if (dir.startsWith("/")) dir else "/$dir") + filename
}