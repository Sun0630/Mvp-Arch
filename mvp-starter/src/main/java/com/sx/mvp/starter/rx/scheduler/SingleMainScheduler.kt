package com.sx.mvp.starter.rx.scheduler

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * 拥有一个线程单例，所有的任务都在这一个线程中执行，
 * 当此线程中有任务执行时，其他任务将会按照先进先出的顺序依次执行。
 *
 * @param T
 */
class SingleMainScheduler<T> private constructor() :
    BaseScheduler<T>(Schedulers.single(), AndroidSchedulers.mainThread())
