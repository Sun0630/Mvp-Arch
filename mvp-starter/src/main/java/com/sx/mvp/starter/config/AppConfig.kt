package com.sx.mvp.starter.config

import android.app.Application

/**
 * @author sunxin
 * @date 2020-01-07 11:27
 * @desc 配置类
 */
object AppConfig {

    const val TAG = "mvp-stater"

    var debug = false

    private var application: Application? = null

    fun init(application: Application) {
        this.application = application
    }

    fun getApplication(): Application {
        if (application == null) {
            throw RuntimeException("Please init in Application#onCreate first!!")
        }
        return application!!
    }


    fun openDebug() {
        debug = true
    }

}