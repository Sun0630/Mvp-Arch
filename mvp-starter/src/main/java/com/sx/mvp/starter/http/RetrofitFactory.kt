package com.sx.mvp.starter.http

import HttpConstant
import com.cxz.kotlin.baselibs.http.interceptor.QueryParameterInterceptor
import com.sx.mvp.starter.config.AppConfig
import com.sx.mvp.starter.http.interceptor.CacheInterceptor
import com.sx.mvp.starter.http.interceptor.CookieInterceptor
import com.sx.mvp.starter.http.interceptor.HeaderInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * @author sunxin
 * @date 2020-01-07 16:49
 * @desc Retrofit 请求
 */
abstract class RetrofitFactory<T> {

    var service: T

    private var mBaseUrl = ""

    private var retrofit: Retrofit? = null

    abstract fun baseUrl(): String


    abstract fun apiService(): Class<T>


    init {
        mBaseUrl = this.baseUrl()
        if (mBaseUrl.isEmpty()) {
            throw RuntimeException("base url can't be null!!")
        }
        service = getRetrofit()!!.create(this.apiService())
    }


    private fun getRetrofit(): Retrofit? {
        if (retrofit == null) {
            synchronized(RetrofitFactory::class.java) {
                if (retrofit == null) {
                    retrofit = Retrofit.Builder()
                        .baseUrl(mBaseUrl)
                        .client(attachOkHttpClient())
                        .addConverterFactory(MoshiConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build()
                }
            }
        }
        return retrofit
    }


    fun attachOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient().newBuilder()
        val httpLoggingInterceptor = HttpLoggingInterceptor()

        if (AppConfig.debug) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        // 设置请求缓存得大小和位置
        val cacheFile = File(AppConfig.getApplication().cacheDir, "cache")
        val cache = Cache(cacheFile, HttpConstant.MAX_CACHE_SIZE)

        builder.run {
            addInterceptor(httpLoggingInterceptor)
            addInterceptor(HeaderInterceptor())
//            addInterceptor(CacheInterceptor())
            addInterceptor(CookieInterceptor())
            addInterceptor(QueryParameterInterceptor())
            cache(cache)
            connectTimeout(HttpConstant.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(HttpConstant.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(HttpConstant.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            retryOnConnectionFailure(true)
        }

        return builder.build()
    }


}