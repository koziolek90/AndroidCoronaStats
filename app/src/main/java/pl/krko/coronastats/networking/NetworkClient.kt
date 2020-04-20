package pl.krko.coronastats.networking

import okhttp3.OkHttpClient
import pl.krko.coronastats.BuildConfig
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import java.net.Proxy
import java.util.concurrent.TimeUnit

/* Copyright (C) Mediaflex Sp. z o. o. - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Michał Daniel <michal.daniel@mediaflex.pl>, 09.08.19
 */


internal object NetworkClient {

    private const val BASE_URL = BuildConfig.API_URL

    private val okHttpClient by lazy {
        OkHttpClient().newBuilder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .callTimeout(10, TimeUnit.SECONDS)
            .proxy(Proxy.NO_PROXY)
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(JacksonConverterFactory.create())
            .build()
    }

    val requestsApi: RequestsApi by lazy {
        retrofit.create(RequestsApi::class.java)
    }
}

