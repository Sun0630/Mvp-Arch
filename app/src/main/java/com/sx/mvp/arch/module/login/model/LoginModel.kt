package com.sx.mvp.arch.module.login.model

import android.annotation.SuppressLint
import com.sx.mvp.arch.module.login.contract.LoginContract
import com.sx.mvp.starter.mvp.BaseModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * @author sunxin
 * @date 2020-01-07 15:22
 * @desc
 */
@SuppressLint("CheckResult")
class LoginModel : BaseModel(), LoginContract.Model {

    override fun login(username: String, password: String, onSuccess: () -> Unit) {
        Observable
            .timer(3000, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { onSuccess() }
    }
}