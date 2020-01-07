package com.sx.mvp.arch.api

import com.sx.mvp.arch.constant.Constant
import com.sx.mvp.starter.http.RetrofitFactory

/**
 * @author sunxin
 * @date 2020-01-07 18:58
 * @desc
 */
object MainRetrofit : RetrofitFactory<ApiService>() {

    override fun baseUrl(): String = Constant.BASE_URL

    override fun apiService(): Class<ApiService> = ApiService::class.java

}