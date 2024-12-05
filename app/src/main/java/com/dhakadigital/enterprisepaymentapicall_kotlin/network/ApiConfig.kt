package com.dhakadigital.enterprisepaymentapicall_kotlin.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object {
        fun getApiService(): ApiService {

            // API response interceptor
            val loggingInterceptor = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)

            // Client
            val client = OkHttpClient.Builder()
                .addInterceptor(ServiceInterceptor())
                .build()

            // Retrofit
            val retrofit = Retrofit.Builder()
                .baseUrl("http://finifyapi.redltd.tech:8052/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            //have to intregate debugger to debug api
            //dagger hilt also have to implement
            //after a long days
            // kotlin view binding
            //scpakscpkasc
            //new code

            return retrofit.create(ApiService::class.java)
        }
    }
}