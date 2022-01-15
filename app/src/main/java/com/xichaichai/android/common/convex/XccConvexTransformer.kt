package com.xichaichai.android.common.convex

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import com.xichaichai.android.base.BaseResponse
import org.paradisehell.convex.transformer.ConvexTransformer
import java.io.IOException
import java.io.InputStream

class XccConvexTransformer : ConvexTransformer {
    private val gson = Gson()

    @Throws(IOException::class)
    override fun transform(original: InputStream): InputStream {
        // 先反序列化为 BaseResponse
        val response = gson.fromJson<BaseResponse<JsonElement>>(
            original.reader(),
            object : TypeToken<BaseResponse<JsonElement>>() {
            }.type
        )
        // 判断 Response 是否成功
        // 成功则将 data 数据转换成 InputStream, 最后由具体 Converter 处理
        if (response.code == 200 && response.data != null) {
            return response.data.toString().byteInputStream()
        }
        throw IOException(
            "errorCode : " + response.code + " ; errorMsg : " + response.msg
        )
    }
}