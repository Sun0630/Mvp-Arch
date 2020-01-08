package com.sx.mvp.arch.module.login.model

import android.annotation.SuppressLint
import com.sx.mvp.arch.api.RetrofitHttp
import com.sx.mvp.arch.data.bean.Banner
import com.sx.mvp.arch.data.bean.HttpResult
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
        return RetrofitHttp.service.login(username, password)
    }

    override fun logout(): Observable<HttpResult<Any>> {
        return RetrofitHttp.service.logout()
    }

    override fun getBannerList(): Observable<HttpResult<MutableList<Banner>>> {
        return RetrofitHttp.service.getBannerList()
    }

}