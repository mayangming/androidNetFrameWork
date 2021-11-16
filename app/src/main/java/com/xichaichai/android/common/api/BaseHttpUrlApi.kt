package com.xichaichai.android.common.api

import androidx.viewbinding.BuildConfig

object BaseHttpUrlApi {
    //基本路径
    val BASE_URL = if (BuildConfig.DEBUG) "http://api.drkktfy.cn/api_v1/" else "http://api.drkktfy.cn/api_v1/"

}