package com.sx.mvp.starter.http.interceptor

import com.sx.mvp.starter.utils.Preference
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author sunxin
 * @date 2020-01-07 17:06
 * @desc 请求头拦截
 */
class HeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newBuilder = request.newBuilder()
        newBuilder.addHeader("Content-type", "application/json; charset=utf-8")
//            .header("token",token)

        val domain = request.url.host
        if (domain.isNotEmpty()){
            val spDomain:String by Preference(domain,"")
            val cookie :String = if (spDomain.isNotEmpty()) spDomain else ""
            if (cookie.isNotEmpty()){
                // 将 Cookie 添加到请求头
                newBuilder.addHeader(HttpConstant.COOKIE_NAME,cookie)
            }
        }


        return chain.proceed(newBuilder.build())
    }
}