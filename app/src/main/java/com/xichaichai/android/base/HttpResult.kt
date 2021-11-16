package com.xichaichai.android.base

//对于Http请求结果的一个处理
sealed class HttpResult<out R> {
    data class Success<out T>(val data: T) : HttpResult<T>()
    data class Error(val exception: Exception) : HttpResult<Nothing>()
}