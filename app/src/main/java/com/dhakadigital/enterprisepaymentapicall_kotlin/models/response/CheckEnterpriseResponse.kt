package com.dhakadigital.enterprisepaymentapicall_kotlin.models.response

data class CheckEnterpriseResponse(
    val errors: String,
    val issuccess: Boolean,
    val message: String,
    val statusCode: Int
)