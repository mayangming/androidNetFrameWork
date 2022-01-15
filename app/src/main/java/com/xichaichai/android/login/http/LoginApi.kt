package com.xichaichai.android.login.http

import com.xichaichai.android.common.convex.XccConvexTransformer
import com.xichaichai.android.login.bean.LoginBean
import org.paradisehell.convex.annotation.Transformer
import retrofit2.http.GET

/**
 * 登陆API
 */
interface LoginApi {
    @GET("banner/index")
    // 为改方法指定 ConvexTransformer, 这样就可以将 BaseResponse 转换成 data 了
    @Transformer(XccConvexTransformer::class)
    suspend fun testBaidu(): List<LoginBean>

}