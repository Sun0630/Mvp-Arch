package com.sx.mvp.starter.ext

import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.sx.mvp.starter.config.AppConfig

/**
 * @author sunxin
 * @date 2020-01-08 14:04
 * @desc 日志
 */

fun Application.initLogger(isDebug: Boolean = true) {

    val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
        .tag(AppConfig.TAG)
        .build()
    Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
        override fun isLoggable(priority: Int, tag: String?): Boolean {
            return isDebug
        }
    })

    loge("initLogger SuccessFully,isDebug=${isDebug}")
}

fun Any.loge(content: String?) {
    Logger.e(content ?: "")
}