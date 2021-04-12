package com.santiago.themoviedbtest.data.remote.interceptor

import com.santiago.themoviedbtest.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class RequestInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url
        val apiKey = BuildConfig.API_KEY
        val url = originalUrl.newBuilder()
            .addQueryParameter("api_key", apiKey)
            .build()

        val requestBuilder = originalRequest.newBuilder().url(url)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}