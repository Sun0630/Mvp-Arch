package com.sx.mvp.arch.module.login

import android.widget.Button
import com.sx.mvp.arch.R
import com.sx.mvp.arch.module.login.contract.LoginContract
import com.sx.mvp.arch.module.login.presenter.LoginPresenter
import com.sx.mvp.starter.base.BaseMvpTitleActivity
import com.sx.mvp.starter.ext.setSingleClickListener
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseMvpTitleActivity<LoginContract.View, LoginContract.Presenter>(),
    LoginContract.View {

    override fun attachChildLayoutId() = R.layout.activity_login

    override fun createPresenter(): LoginContract.Presenter = LoginPresenter()

    override fun hasBackIcon(): Boolean = false

    override fun initView() {
        super.initView()

        setBaseTitle("登录")
    }


    override fun initData() {
        btn_login.setSingleClickListener {
            val username = username.text.toString()
            val password = password.text.toString()
            mPresenter?.login(username, password)
        }

    }

    override fun showLoading() {
        println("---------show loading....------------")
    }

    override fun hideLoading() {
        println("---------hide loading....------------")
    }


    override fun start() {
    }

    override fun loginSuccess() {
        showDefaultMsg("登录成功")
    }


}
