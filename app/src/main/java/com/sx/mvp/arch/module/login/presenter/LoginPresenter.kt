package com.sx.mvp.arch.module.login.presenter

import com.sx.mvp.arch.module.login.contract.LoginContract
import com.sx.mvp.arch.module.login.model.LoginModel
import com.sx.mvp.starter.ext.exec
import com.sx.mvp.starter.mvp.BasePresenter

/**
 * @author sunxin
 * @date 2020-01-07 15:22
 * @desc
 */
class LoginPresenter : BasePresenter<LoginContract.Model, LoginContract.View>(),

    LoginContract.Presenter {

    override fun createModel(): LoginContract.Model? = LoginModel()

    override fun login(username: String, password: String) {
        mModel?.login(username, password)?.exec(mModel, mView, onSuccess = {
            mView?.loginSuccess()
        })

    }

    override fun logout() {
        mModel?.logout()?.exec(mModel,mView,onSuccess = {
            mView?.logoutSuccess()
        })
    }

    override fun getBanner() {
        mModel?.getBannerList()?.exec(mModel, mView, onSuccess = {
            mView?.showBanner(it.data)
        })
    }

}