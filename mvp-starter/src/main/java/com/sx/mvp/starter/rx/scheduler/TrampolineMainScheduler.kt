package com.sx.mvp.starter.rx.scheduler

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * 在当前线程立即执行任务，如果当前线程有任务在执行，
 * 则会将其暂停，等插入进来的任务执行完之后，再将未完成的任务接着执行
 *
 * @param T
 */
class TrampolineMainScheduler<T> private constructor() :
    BaseScheduler<T>(Schedulers.trampoline(), AndroidSchedulers.mainThread())
