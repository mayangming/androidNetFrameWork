package com.xichaichai.android.login.http

import com.xichaichai.android.base.HttpResult
import com.xichaichai.android.common.manager.RetrofitManager
import com.xichaichai.android.login.bean.LoginBean

class LoginRepository {
    suspend fun login(): HttpResult<List<LoginBean>>{
        val testApi = RetrofitManager.create(LoginApi::class.java)
//            val testBaidu = runCatching {
//                HttpResult.Success(testApi.testBaidu())
//            }.onSuccess {
//                return@onSuccess
////                HttpResult.Error(Exception("Cannot open HttpURLConnection"))
//            }.onFailure {
//                it.printStackTrace()
////                return@onFailure
//                HttpResult.Error(Exception("Cannot open HttpURLConnection"))
//            }
//        println("YM----->:${testBaidu}")
        val result = try {
            HttpResult.Success(testApi.testBaidu())
        }catch (e: Exception){
            HttpResult.Error(e)

        }
        return result
    }
}