package com.sx.mvp.arch.module.login.contract

import com.sx.mvp.arch.data.bean.Banner
import com.sx.mvp.arch.data.bean.HttpResult
import com.sx.mvp.starter.mvp.IModel
import com.sx.mvp.starter.mvp.IPresenter
import com.sx.mvp.starter.mvp.IView
import io.reactivex.Observable

/**
 * @author sunxin
 * @date 2020-01-07 15:22
 * @desc 登录契约类
 */
interface LoginContract {

    interface View : IView {
        fun loginSuccess()

        fun logoutSuccess()

        fun showBanner(bannerList: MutableList<Banner>)
    }

    interface Presenter : IPresenter<View> {
        fun login(username: String, password: String)

        fun logout()

        fun getBanner()
    }

    interface Model : IModel {
        fun login(username: String, password: String): Observable<HttpResult<Any>>

        fun logout(): Observable<HttpResult<Any>>

        fun getBannerList(): Observable<HttpResult<MutableList<Banner>>>
    }

}