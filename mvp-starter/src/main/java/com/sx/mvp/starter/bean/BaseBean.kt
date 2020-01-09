package com.sx.mvp.starter.bean

import com.squareup.moshi.Json

/**
 * @author sunxin
 * @date 2020-01-07 14:23
 * @desc 实体类基类
 */
open class BaseBean {
    var errorCode: Int = 0
    var errorMsg: String = ""
}