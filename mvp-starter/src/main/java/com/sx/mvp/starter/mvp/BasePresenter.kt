package com.sx.mvp.starter.mvp

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.greenrobot.eventbus.EventBus

/**
 * @author sunxin
 * @date 2020-01-07 11:58
 * @desc
 */
abstract class BasePresenter<M : IModel, V : IView> : IPresenter<V>, LifecycleObserver {

    protected var mView: V? = null
    protected var mModel: M? = null

    private val isViewAttached: Boolean
        get() = mView != null

    private var mCompositeDisposable: CompositeDisposable? = null


    /**
     * 创建Model
     *
     * @return
     */
    abstract fun createModel(): M?

    /**
     * 是否使用EventBus
     *
     * @return
     */
    open fun useEventBus(): Boolean = false

    override fun attachView(view: V) {
        this.mView = view
        mModel = createModel()
        if (mView is LifecycleOwner) {
            (mView as LifecycleOwner).lifecycle.addObserver(this)
            if (mModel != null && mModel is LifecycleObserver) {
                (mView as LifecycleOwner).lifecycle.addObserver(mModel as LifecycleObserver)
            }
        }

        if (useEventBus()) {
            EventBus.getDefault().register(this)
        }
    }

    override fun detachView() {
        // 取消订阅
        if (useEventBus()) {
            EventBus.getDefault().unregister(this)
        }
        unDispose()
        mModel?.onDetach()
        mView = null
        mModel = null

    }

    open fun addDisposable(disposable: Disposable?) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = CompositeDisposable()
        }
        disposable?.let {
            mCompositeDisposable?.add(it)
        }
    }


    private fun unDispose() {
        mCompositeDisposable?.clear()
        mCompositeDisposable = null
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(owner: LifecycleOwner) {
        owner.lifecycle.removeObserver(this)
    }

    open fun checkViewAttached() {
        if (!isViewAttached) {
            throw MvpViewNotAttachedException()
        }
    }

    private class MvpViewNotAttachedException internal constructor() :
        RuntimeException("Please call IPresenter.attachView(IBaseView) before" + " requesting data to the IPresenter")


}