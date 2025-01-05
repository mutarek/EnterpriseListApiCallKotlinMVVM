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
            //val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
            val client = OkHttpClient.Builder()
                .addInterceptor(ServiceInterceptor())
                .build()
            //test purpose
            //test purpose
            val retrofit = Retrofit.Builder()
                .baseUrl("http://gps.motolockbd.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            //have to work kotlin multiplatform

            return retrofit.create(ApiService::class.java)
        }
    }
}