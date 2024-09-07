package com.dhakadigital.enterprisepaymentapicall_kotlin.models.response

data class EnterpriseListResponse(
    val issuccess: Boolean,
    val message: String,
    val payload: List<Payload>,
    val statusCode: Int
)