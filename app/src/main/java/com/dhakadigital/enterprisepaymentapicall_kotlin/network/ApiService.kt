package com.dhakadigital.enterprisepaymentapicall_kotlin.network

import com.dhakadigital.enterprisepaymentapicall_kotlin.models.response.EnterpriseListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface ApiService {

    @GET("femp/enterprise/list")
    fun getEnterpriseList() : Call<EnterpriseListResponse>
}