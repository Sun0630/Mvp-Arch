package com.sx.mvp.arch.api

import com.sx.mvp.arch.data.bean.Banner
import com.sx.mvp.arch.data.bean.HttpResult
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * @author sunxin
 * @date 2020-01-07 18:47
 * @desc
 */
interface ApiService {


    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    @POST("user/login")
    @FormUrlEncoded
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Observable<HttpResult<Any>>


    /**
     * 获取轮播图
     *
     * @return
     */
    @GET("banner/json")
    fun getBannerList(): Observable<HttpResult<MutableList<Banner>>>


    /**
     * 退出登录
     *
     */
    @GET("user/logout/json")
    fun logout(): Observable<HttpResult<Any>>

}