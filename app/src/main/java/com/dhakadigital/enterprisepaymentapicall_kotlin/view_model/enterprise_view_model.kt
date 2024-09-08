package com.dhakadigital.enterprisepaymentapicall_kotlin.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dhakadigital.enterprisepaymentapicall_kotlin.models.request.CheckEnterpriseRequest
import com.dhakadigital.enterprisepaymentapicall_kotlin.models.response.CheckEnterpriseResponse
import com.dhakadigital.enterprisepaymentapicall_kotlin.models.response.EnterpriseListResponse
import com.dhakadigital.enterprisepaymentapicall_kotlin.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EnterpriseViewModel() : ViewModel() {
    private val _enterpriseListData = MutableLiveData<EnterpriseListResponse?>()
    val enterpriseListData: MutableLiveData<EnterpriseListResponse?> get() = _enterpriseListData

    private val _checkMerchantData = MutableLiveData<CheckEnterpriseResponse?>()
    val checkMerchantData: MutableLiveData<CheckEnterpriseResponse?> get() = _checkMerchantData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> get() = _isError


    var errorMessage: String = ""
        private set

    init {
        getEnterpriseListData()
    }

    private fun getEnterpriseListData() {
        _isLoading.value = true
        _isError.value = false
        val client = ApiConfig.getApiService().getEnterpriseList()
        client.enqueue(object : Callback<EnterpriseListResponse> {

            override fun onResponse(
                call: Call<EnterpriseListResponse>,
                response: Response<EnterpriseListResponse>
            ) {
                val responseBody = response.body()
                if (!response.isSuccessful || responseBody == null) {
                    onError("Data Processing Error")
                    return
                }

                _isLoading.value = false
                _enterpriseListData.postValue(responseBody)
            }

            override fun onFailure(call: Call<EnterpriseListResponse>, t: Throwable) {
                onError(t.message)
                t.printStackTrace()
            }

        })
    }

    private fun onError(inputMessage: String?) {

        val message = if (inputMessage.isNullOrBlank() or inputMessage.isNullOrEmpty()) "Unknown Error"
        else inputMessage

        errorMessage = StringBuilder("ERROR: ")
            .append("$message some data may not displayed properly").toString()

        _isError.value = true
        _isLoading.value = false
    }

    fun checkMarchantList(enterpriseID: String){
        _isLoading.value = true
        _isError.value = false
        val body = CheckEnterpriseRequest(enterpriseID)
        val client = ApiConfig.getApiService().checkEnterpriseMerchant(body)

        client.enqueue(object : Callback<CheckEnterpriseResponse> {
            override fun onResponse(
                call: Call<CheckEnterpriseResponse>,
                response: Response<CheckEnterpriseResponse>
            ) {
                val responseBody = response.body()
                if (!response.isSuccessful || responseBody == null) {
                    onError("Data Processing Error")
                    return
                }

                _isLoading.value = false
                _checkMerchantData.postValue(responseBody)
            }

            override fun onFailure(call: Call<CheckEnterpriseResponse>, t: Throwable) {
                onError(t.message)
                t.printStackTrace()
            }


        })
    }
}