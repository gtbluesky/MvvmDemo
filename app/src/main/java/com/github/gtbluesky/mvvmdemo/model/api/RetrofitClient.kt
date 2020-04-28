package com.github.gtbluesky.mvvmdemo.model.api

import com.github.gtbluesky.mvvmdemo.util.HttpsUtil
import com.github.gtbluesky.mvvmdemo.util.isDebug
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitClient {

    private const val TIME_OUT = 5L
    val request by lazy {
        getRequestApi(RequestServiceApi::class.java, RequestServiceApi.BASE_URL)
    }

    private val okHttpClient: OkHttpClient
        get() {
            val builder = OkHttpClient.Builder()
            val loggingInterceptor = HttpLoggingInterceptor()

            if (isDebug()) {
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            } else {
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
            }
            val sslParams = HttpsUtil.getSslSocketFactory()
            builder.addInterceptor(loggingInterceptor)
                .addInterceptor(HttpParamInterceptor())
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .sslSocketFactory(sslParams.sslSocketFactory, sslParams.trustManager)
                .hostnameVerifier(HttpsUtil.UnSafeHostnameVerifier)
            return builder.build()
        }

    private fun <T> getRequestApi(clz: Class<T>, baseUrl: String): T {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
            .create(clz)
    }
}