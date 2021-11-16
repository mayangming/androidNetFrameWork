package com.xichaichai.android.common.manager

import com.google.gson.Gson
import com.google.gson.GsonBuilder

/**
 * 得到gson实例
 *
 * @return
 */
fun getGson(): Gson {
    return GsonHolder.gson
}

/**
 * 使用holder模式创建单例
 */
private object GsonHolder {
    var gson: Gson = GsonBuilder()
        .create()
}