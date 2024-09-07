package com.dhakadigital.enterprisepaymentapicall_kotlin.network

import com.dhakadigital.enterprisepaymentapicall_kotlin.models.request.CheckEnterpriseRequest
import com.dhakadigital.enterprisepaymentapicall_kotlin.models.response.CheckEnterpriseResponse
import com.dhakadigital.enterprisepaymentapicall_kotlin.models.response.EnterpriseListResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @GET("femp/enterprise/list")
    fun getEnterpriseList() : Call<EnterpriseListResponse>

    @POST("femp/enterprise/merchant-list")
    fun checkEnterpriseMerchant(@Body request : CheckEnterpriseRequest) : Call<CheckEnterpriseResponse>
}