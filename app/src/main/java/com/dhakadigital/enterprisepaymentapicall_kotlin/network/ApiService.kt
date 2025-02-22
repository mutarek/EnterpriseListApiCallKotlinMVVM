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

    @GET("add_device_data?device_id=2104&user_api_hash=%242y%2410%24n1dr.eY9XRI.7qH5t3Yzrelo4evrlZtSUjjEN56RLuThLvRHe7Y7C&lang=en")
    fun getEnterpriseList() : Call<EnterpriseListResponse>

    @POST("femp/enterprise/merchant-list")
    fun checkEnterpriseMerchant(@Body request : CheckEnterpriseRequest) : Call<CheckEnterpriseResponse>
}