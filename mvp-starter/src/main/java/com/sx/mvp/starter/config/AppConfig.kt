package com.sx.mvp.starter.config

import android.app.Application
import com.billy.android.loading.Gloading
import com.sx.mvp.starter.loading.GlobalAdapter


/**
 * @author sunxin
 * @date 2020-01-07 11:27
 * @desc 配置类
 */
object AppConfig {

    const val TAG = "mvp-stater"

    const val HIDE_LOADING_STATUS_MSG = "hide_loading_status_msg"

    var debug = true

    private var application: Application? = null

    fun init(application: Application) {
        this.application = application
        initLoading()
    }

    private fun initLoading() {
        Gloading.debug(debug)
        Gloading.initDefault(GlobalAdapter())
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