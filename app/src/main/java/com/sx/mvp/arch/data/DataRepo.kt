package com.sx.mvp.arch.data

import com.squareup.moshi.Json
import com.sx.mvp.starter.bean.BaseBean

/**
 * @author sunxin
 * @date 2020-01-07 18:53
 * @desc
 */

data class HttpResult<T> (@Json(name="data") val data:T) :BaseBean()


// 登录数据
data class LoginData(
    @Json(name = "chapterTops") val chapterTops: MutableList<String>,
    @Json(name = "collectIds") val collectIds: MutableList<String>,
    @Json(name = "email") val email: String,
    @Json(name = "icon") val icon: String,
    @Json(name = "id") val id: Int,
    @Json(name = "password") val password: String,
    @Json(name = "token") val token: String,
    @Json(name = "type") val type: Int,
    @Json(name = "username") val username: String
)