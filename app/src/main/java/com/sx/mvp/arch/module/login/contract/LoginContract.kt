package com.sx.mvp.arch.module.login.contract

import com.sx.mvp.arch.data.HttpResult
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
    }

    interface Presenter : IPresenter<View> {
        fun login(username: String, password: String)
    }

    interface Model : IModel {
        fun login(username: String, password: String):Observable<HttpResult<Any>>
    }

}