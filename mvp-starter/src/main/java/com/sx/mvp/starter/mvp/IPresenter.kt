package com.sx.mvp.starter.mvp

/**
 * @author sunxin
 * @date 2020-01-07 11:54
 * @desc
 */
interface IPresenter<in V : IView> {

    /**
     * 绑定视图
     *
     * @param view
     */
    fun attachView(view: V)


    /**
     * 解绑视图
     */
    fun detachView()

}