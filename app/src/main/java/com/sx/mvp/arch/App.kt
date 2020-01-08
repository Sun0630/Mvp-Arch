package com.sx.mvp.arch

import android.app.Application
import com.sx.mvp.starter.config.AppConfig
import com.sx.mvp.starter.ext.initLogger

/**
 * @author sunxin
 * @date 2020-01-07 14:26
 * @desc
 */
class App:Application() {

    override fun onCreate() {
        super.onCreate()
        initApp()
        initLogger()
    }

    private fun initApp() {
        AppConfig.init(this)
        AppConfig.openDebug()
    }
}