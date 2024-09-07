package com.dhakadigital.enterprisepaymentapicall_kotlin.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dhakadigital.enterprisepaymentapicall_kotlin.models.response.EnterpriseListResponse
import com.dhakadigital.enterprisepaymentapicall_kotlin.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EnterpriseViewModel() : ViewModel() {
    private val _enterpriseListData = MutableLiveData<EnterpriseListResponse?>()
    val weatherData: MutableLiveData<EnterpriseListResponse?> get() = _enterpriseListData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> get() = _isError


    var errorMessage: String = ""
        private set

    suspend fun getWeatherData() {

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
}