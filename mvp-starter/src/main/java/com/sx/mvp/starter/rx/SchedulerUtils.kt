package com.sx.mvp.starter.rx

import com.sx.mvp.starter.rx.scheduler.IoMainScheduler


object SchedulerUtils {

    fun <T> ioToMain(): IoMainScheduler<T> = IoMainScheduler()

}