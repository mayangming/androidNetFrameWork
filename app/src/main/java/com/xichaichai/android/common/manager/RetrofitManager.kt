package com.xichaichai.android.common.manager

import android.util.Log
import com.xichaichai.android.common.api.BaseHttpUrlApi
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import org.paradisehell.convex.converter.ConvexConverterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

class RetrofitManager {
    init {
        retrofitCreate()
    }
    companion object {
        private var mInstance : Retrofit? = null
        //        private val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        private fun retrofitCreate(): Retrofit {
            val logger = HttpLoggingInterceptor {
                Log.d("API", it)
            }
            logger.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .protocols(
                    Collections.unmodifiableList(
                        arrayListOf(
                            Protocol.HTTP_1_1,
                            Protocol.HTTP_2)))//启用http2.0协议
                .build()
            return Retrofit.Builder()
                .baseUrl(BaseHttpUrlApi.BASE_URL)
                .client(client)
                // 一定将 ConvexConverterFactory 放在所有 Converter.Factory 的前面
                .addConverterFactory(ConvexConverterFactory())
                .addConverterFactory(GsonConverterFactory.create(getGson())).build()
//                .addConverterFactory(MoshiConverterFactory.create(moshi))
        }

        private fun getInstance() : Retrofit {
            if (mInstance == null) {
                val var1 = RetrofitManager::class.java
                synchronized(RetrofitManager::class.java) {
                    if (mInstance == null) {
                        mInstance = retrofitCreate()
                    }
                }
            }
            return mInstance!!
        }

        fun <T>  create(service:  Class<T>) : T{
            return getInstance().create(service)
        }
    }
}