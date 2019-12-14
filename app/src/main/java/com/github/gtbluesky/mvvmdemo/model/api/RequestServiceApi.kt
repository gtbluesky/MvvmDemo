package com.github.gtbluesky.mvvmdemo.model.api

import com.github.gtbluesky.mvvmdemo.util.isDebug
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface RequestServiceApi {

    companion object {
        const val BASE_URL = "http://www.baidu.com"
    }

    @GET("/{dev}task/count/getStatInfo")
    suspend fun getStatInfo(
            @Path(value = "dev", encoded = true) dev: String = if (isDebug()) "dev/" else "",
            @Query("access_token") accessToken: String = "",
            @Query("type") type: Int = 4
    ): Response<ResponseBody>

    @GET("http://super-admin{dev}.com/menu_show")
    suspend fun getPersonalMenu(
            @Path(value = "dev", encoded = true) dev: String = if (isDebug()) "-dev" else "",
            @Query("access_token") accessToken: String = ""
    ): Response<ResponseBody>
}