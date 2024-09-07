package com.dhakadigital.enterprisepaymentapicall_kotlin.network

import okhttp3.Interceptor
import okhttp3.Response

class ServiceInterceptor : Interceptor {

    var token: String =
        "Basic ODgwMTk4MzQ3Njg4ODo2Y2E1MDRkOTAxN2JjOTM4YmE4M2RmN2UzNmI2NmNhNGU0NzlhMjE4MzA2N2E2ZTNiOTJjNTRjZWQyNjFkMjM5";

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val finalToken = "$token"
        request = request.newBuilder()
            .addHeader("Authorization", finalToken)
            .addHeader("module", "JW9tc0ByZWRsdGQl")
            .build()

        return chain.proceed(request)
    }

}