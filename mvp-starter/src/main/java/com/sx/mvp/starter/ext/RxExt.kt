package com.sx.mvp.starter.ext

import com.sx.mvp.starter.bean.BaseBean
import com.sx.mvp.starter.http.constant.HttpStatus
import com.sx.mvp.starter.http.exception.ExceptionHandle
import com.sx.mvp.starter.http.function.RetryWhenDelay
import com.sx.mvp.starter.mvp.IModel
import com.sx.mvp.starter.mvp.IView
import com.sx.mvp.starter.rx.SchedulerUtils
import com.sx.mvp.starter.utils.NetWorkUtil
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable


fun <T : BaseBean> Observable<T>.exec(
    model: IModel?,
    view: IView?,
    isShowLoading: Boolean = true,
    onSuccess: (T) -> Unit,
    onError: ((T) -> Unit)? = null
) {
    this.compose(SchedulerUtils.ioToMain())
        .retryWhen(RetryWhenDelay())
        .subscribe(object : Observer<T> {
            override fun onComplete() {
                view?.hideLoading()
            }

            override fun onSubscribe(d: Disposable) {
                if (isShowLoading) view?.showLoading()
                model?.addDisposable(d)
                if (!NetWorkUtil.isConnected()) {
                    view?.showDefaultMsg("当前网络不可用，请检查网络设置")
                    d.dispose()
                    onComplete()
                }
            }

            override fun onNext(t: T) {
//                view?.hideLoading()
                view?.showLoadingSuccess()
                when {
                    t.errorCode == HttpStatus.SUCCESS -> onSuccess(t)
                    t.errorCode == HttpStatus.TOKEN_INVALID -> {
                        // Token 过期，重新登录
                    }
                    else -> {
                        if (onError != null) {
                            onError.invoke(t)
                        } else {
                            if (t.errorMsg.isNotEmpty())
                                view?.showDefaultMsg(t.errorMsg)
                        }
                    }
                }
            }

            override fun onError(t: Throwable) {
//                view?.hideLoading()
                view?.showLoadingFailed()
                view?.showError(ExceptionHandle.handleException(t))
            }
        })
}

fun <T : BaseBean> Observable<T>.execs(
    view: IView?,
    isShowLoading: Boolean = true,
    onSuccess: (T) -> Unit,
    onError: ((T) -> Unit)? = null
): Disposable {
    if (isShowLoading) view?.showLoading()
    return this.compose(SchedulerUtils.ioToMain())

        .retryWhen(RetryWhenDelay())
        .subscribe({
            if (isShowLoading) view?.showLoading()
            when {
                it.errorCode == HttpStatus.SUCCESS -> onSuccess.invoke(it)
                it.errorCode == HttpStatus.TOKEN_INVALID -> {
                    // Token 过期，重新登录
                }
                else -> {
                    if (onError != null) {
                        onError.invoke(it)
                    } else {
                        if (it.errorMsg.isNotEmpty())
                            view?.showDefaultMsg(it.errorMsg)
                    }
                }
            }
        }, {
            view?.showLoadingFailed()
            view?.showError(ExceptionHandle.handleException(it))
        })
}