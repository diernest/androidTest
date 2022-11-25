package com.example.ejercicio.core.data.remote.interceptor

import com.example.ejercicio.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.newBuilder().addQueryParameter(
            "api_key", value = BuildConfig.API_KEY
        ).build()
        val newRequest = request.newBuilder().url(url).build()
        return chain.proceed(newRequest)
    }

}