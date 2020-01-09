package com.sx.mvp.starter.base

import com.billy.android.loading.Gloading
import com.sx.mvp.starter.ext.showToast
import com.sx.mvp.starter.mvp.IPresenter
import com.sx.mvp.starter.mvp.IView

/**
 * @author sunxin
 * @date 2020-01-07 14:10
 * @desc
 */
abstract class BaseMvpActivity<in V : IView, P : IPresenter<V>> : BaseActivity(), IView {

    protected var mPresenter: P? = null

    private var mHolder: Gloading.Holder? = null

    protected abstract fun createPresenter(): P

    override fun initView() {
        mPresenter = createPresenter()
        mPresenter?.attachView(this as V)
    }

    private fun initLoadingStatusViewIfNeed() {
        if (mHolder == null) {
            mHolder = Gloading.getDefault().wrap(this).withRetry {
                onLoadRetry()
            }
        }
    }

    /**
     * 点击重试
     *
     */
    abstract fun onLoadRetry()


    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.detachView()
        mPresenter = null
    }


    override fun showLoading() {
        initLoadingStatusViewIfNeed()
        mHolder?.showLoading()
    }

    override fun hideLoading() {

    }

    override fun showLoadingSuccess() {
        initLoadingStatusViewIfNeed()
        mHolder?.showLoadSuccess()
    }

    override fun showLoadingFailed() {
        initLoadingStatusViewIfNeed()
        mHolder?.showLoadFailed()
    }

    override fun showEmpty() {
        initLoadingStatusViewIfNeed()
        mHolder?.showEmpty()
    }

    override fun showDefaultMsg(msg: String) {
        showToast(msg)
    }

    override fun showMsg(msg: String) {
        showToast(msg)
    }

    override fun showError(errorMsg: String) {
        showToast(errorMsg)
    }


}