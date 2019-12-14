package com.github.gtbluesky.mvvmdemo.model.api

import okhttp3.Interceptor
import okhttp3.Response

class HttpParamInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val header = request
            .headers()
            .newBuilder()
            .add("User-Agent", "")
            .build()

        val newRequest = request.newBuilder()
            .headers(header)
            .build()

        return chain.proceed(newRequest)
    }
}