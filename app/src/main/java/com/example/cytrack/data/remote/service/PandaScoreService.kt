package com.example.cytrack.data.remote.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PandaScoreService {
    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val newUrl = chain.request().url.newBuilder()
                    .build()

                val request = chain.request()
                    .newBuilder()
                    .url(newUrl)
                    .addHeader("accept", "application/json")
                    .addHeader(
                        "authorization",
                        "Bearer xgcR6Ooh7lJ21X5xWoWfCNvVChwrOi5BQmEr3Ff7sXncISj8bww"
                    )
                    .build()

                chain.proceed(request)
            }
            .addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            })
            .build()
    }

    val instance: PandaScoreApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.pandascore.co/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(PandaScoreApiService::class.java)
    }
}
