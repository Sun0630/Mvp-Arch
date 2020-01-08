package com.sx.mvp.starter.config

import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger.addLogAdapter
import com.orhanobut.logger.PrettyFormatStrategy


/**
 * @author sunxin
 * @date 2020-01-07 11:27
 * @desc 配置类
 */
object AppConfig {

    const val TAG = "mvp-stater"

    var debug = true

    private var application: Application? = null

    fun init(application: Application) {
        this.application = application
        initLogger()
    }

   private fun initLogger(){
        val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(true) // (Optional) Whether to show thread info or not. Default true
            .methodCount(2) // (Optional) How many method line to show. Default 2
            .methodOffset(5) // (Optional) Hides internal method calls up to offset. Default 5
//            .logStrategy(tag) // (Optional) Changes the log strategy to print out. Default LogCat
            .tag(TAG) // (Optional) Global tag for every log. Default PRETTY_LOGGER
            .build()

       addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
           override fun isLoggable(priority: Int, tag: String?): Boolean {
               return debug
           }
       })
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