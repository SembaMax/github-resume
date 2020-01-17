package com.semba.githubresume.api

import com.semba.githubresume.utils.Constants
import okhttp3.Interceptor
import okhttp3.RequestBody
import okhttp3.Response
import okio.Buffer
import java.io.IOException

/**
 * Created by SeMbA on 2019-12-07.
 */
class MyRetrofitInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain?): Response {

        var request = chain!!.request()
        val accept = "application/json"
        val content = "application/json"

        val httpUrl = request.url()

        request = request.newBuilder()
            .addHeader("Accept",accept)
            .addHeader("Content-Type",content)
            .addHeader("Authorization", "token ${Constants.API_KEY}")
            .url(httpUrl)
            .build()

        return chain.proceed(request)
    }
}