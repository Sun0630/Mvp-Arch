package com.sx.mvp.starter.mvp

/**
 * @author sunxin
 * @date 2020-01-07 11:54
 * @desc
 */
interface IView {

    fun showLoading()

    fun hideLoading()

    fun showLoadingFailed()

    fun showLoadingSuccess()

    fun showEmpty()

    fun showDefaultMsg(msg: String)

    fun showMsg(msg:String)

    fun showError(errorMsg:String)

}