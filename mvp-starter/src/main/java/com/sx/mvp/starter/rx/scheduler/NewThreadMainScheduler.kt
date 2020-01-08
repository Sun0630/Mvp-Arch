package com.sx.mvp.starter.rx.scheduler

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * 在每执行一个任务时创建一个新的线程，不具有线程缓存机制
 * Schedulers.newThread( )的效率没有Schedulers.io( )高
 *
 * @param T
 */
class NewThreadMainScheduler<T> private constructor() :
    BaseScheduler<T>(Schedulers.newThread(), AndroidSchedulers.mainThread())
