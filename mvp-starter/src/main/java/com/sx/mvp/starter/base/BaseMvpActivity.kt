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


    protected abstract fun createPresenter(): P

    override fun initView() {
        mPresenter = createPresenter()
        mPresenter?.attachView(this as V)
    }






    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.detachView()
        mPresenter = null
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