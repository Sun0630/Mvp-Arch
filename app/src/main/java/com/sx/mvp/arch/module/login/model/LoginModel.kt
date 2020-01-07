package com.sx.mvp.arch.module.login.model

import android.os.SystemClock
import com.sx.mvp.arch.module.login.contract.LoginContract
import com.sx.mvp.starter.mvp.BaseModel

/**
 * @author sunxin
 * @date 2020-01-07 15:22
 * @desc
 */
class LoginModel:BaseModel(),LoginContract.Model {

    override fun login(username: String, password: String, onSuccess: () -> Unit) {
        SystemClock.sleep(3000)
        onSuccess()
    }
}