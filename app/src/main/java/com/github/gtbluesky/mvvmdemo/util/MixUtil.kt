package com.github.gtbluesky.mvvmdemo.util

import android.content.pm.ApplicationInfo
import com.github.gtbluesky.mvvmdemo.base.BaseApplication
import com.github.gtbluesky.mvvmdemo.model.bean.ResponseBean
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import okhttp3.ResponseBody
import retrofit2.Response


suspend inline fun <reified T> handleResponse(
        response: Response<ResponseBody>,
        crossinline successBlock: suspend CoroutineScope.(T?) -> Unit,
        crossinline errorBlock: suspend CoroutineScope.(String) -> Unit
) {
    coroutineScope {
        if (response.code() == 200) {
            response.body()?.string()?.getResponseBean<T>().let {
                if (it?.code == 200) successBlock(it.data) else errorBlock(it?.msg
                        ?: "Request Error")
            }
        } else {
            errorBlock("Request Error")
        }
    }
}

inline fun <reified T> String.getResponseBean(): ResponseBean<T> = Gson().fromJson(this)

fun isDebug(): Boolean {
    return try {
        val info: ApplicationInfo = BaseApplication.context.applicationInfo
        info.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0
    } catch (e: Exception) {
        false
    }
}