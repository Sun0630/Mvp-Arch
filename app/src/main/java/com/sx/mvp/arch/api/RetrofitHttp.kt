package com.sx.mvp.arch.api

import com.sx.mvp.arch.constant.Constant
import com.sx.mvp.starter.http.RetrofitFactory

/**
 * @author sunxin
 * @date 2020-01-07 18:58
 * @desc
 */
object RetrofitHttp : RetrofitFactory<LoginService>() {

    override fun baseUrl(): String = Constant.BASE_URL

    override fun apiService(): Class<LoginService> = LoginService::class.java

}