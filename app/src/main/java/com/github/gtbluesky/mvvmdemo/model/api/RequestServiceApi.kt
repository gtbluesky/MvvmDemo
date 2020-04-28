package com.github.gtbluesky.mvvmdemo.model.api

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface RequestServiceApi {

    companion object {
        const val BASE_URL = "https://api.github.com"
    }

    @GET("/users/{user}/repos")
    suspend fun getRepos(
            @Path(value = "user", encoded = true) dev: String
    ): Response<ResponseBody>

}