package com.sx.mvp.starter.mvp

import io.reactivex.disposables.Disposable

/**
 * @author sunxin
 * @date 2020-01-07 11:54
 * @desc
 */
interface IModel {

    fun addDisposable(disposable: Disposable?)

    fun onDetach()

}