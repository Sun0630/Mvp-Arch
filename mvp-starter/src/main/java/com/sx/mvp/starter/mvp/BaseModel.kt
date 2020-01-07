package com.sx.mvp.starter.mvp

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * @author sunxin
 * @date 2020-01-07 11:58
 * @desc
 */
abstract class BaseModel : IModel, LifecycleObserver {

    private var mCompositeDisposable: CompositeDisposable? = null

    override fun addDisposable(disposable: Disposable?) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = CompositeDisposable()
        }
        disposable?.let {
            mCompositeDisposable?.add(it)
        }
    }

    override fun onDetach() {
        unDispose()
    }

    private fun unDispose() {
        mCompositeDisposable?.clear()
        mCompositeDisposable = null
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(owner: LifecycleOwner) {
        owner.lifecycle.removeObserver(this)
    }

}