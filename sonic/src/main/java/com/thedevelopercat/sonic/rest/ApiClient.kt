package com.thedevelopercat.sonic.rest

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiClient(baseUrl: String) {

    private var retrofit: Retrofit
    private var gson: Gson? = null

    companion object {
        lateinit var instance: ApiClient
            private set

        fun initApiClient(baseUrl: String){
            instance = ApiClient(baseUrl)
        }

        val client: Retrofit
            get() = instance.retrofit
    }


    init {
        val logging = HttpLoggingInterceptor()
        logging.level = Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        gson = GsonBuilder()
                .create()

        retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson!!))
                .client(httpClient.build())
                .build()
    }
}