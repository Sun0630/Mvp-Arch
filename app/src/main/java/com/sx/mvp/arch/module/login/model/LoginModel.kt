package com.sx.mvp.arch.module.login.model

import android.annotation.SuppressLint
import com.sx.mvp.arch.api.MainRetrofit
import com.sx.mvp.arch.data.HttpResult
import com.sx.mvp.arch.module.login.contract.LoginContract
import com.sx.mvp.starter.mvp.BaseModel
import io.reactivex.Observable

/**
 * @author sunxin
 * @date 2020-01-07 15:22
 * @desc
 */
@SuppressLint("CheckResult")
class LoginModel : BaseModel(), LoginContract.Model {


    override fun login(username: String, password: String): Observable<HttpResult<Any>> {
        return MainRetrofit.service.login(username, password)
    }

}