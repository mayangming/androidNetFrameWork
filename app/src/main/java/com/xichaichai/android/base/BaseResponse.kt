package com.xichaichai.android.base

import com.google.gson.annotations.SerializedName

// 1. 定义 BaseResponse
// 用于处理后台返回的数据进行反序列化，拿到最终的 data 数据
data class BaseResponse<T>(
    @SerializedName("code")
    val code: Int = 0,
    @SerializedName("msg")
    val msg: String? = null,
    @SerializedName("success")
    val success:Boolean = false,
    @SerializedName("data")
    val data: T? = null
)
