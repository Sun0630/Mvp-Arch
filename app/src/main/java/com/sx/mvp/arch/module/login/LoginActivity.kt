package com.sx.mvp.arch.module.login

import android.Manifest
import com.sx.mvp.arch.R
import com.sx.mvp.arch.data.bean.Banner
import com.sx.mvp.arch.module.login.contract.LoginContract
import com.sx.mvp.arch.module.login.presenter.LoginPresenter
import com.sx.mvp.arch.utils.DialogUtil
import com.sx.mvp.starter.base.BaseMvpTitleActivity
import com.sx.mvp.starter.ext.loge
import com.sx.mvp.starter.ext.setSingleClickListener
import com.sx.mvp.starter.ext.showSnackMsg
import com.sx.mvp.starter.glide.loadReveal
import com.sx.mvp.starter.permission.PermissionHelper
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseMvpTitleActivity<LoginContract.View, LoginContract.Presenter>(),
    LoginContract.View {

    private val mDialog by lazy {
        DialogUtil.getWaitDialog(this,"正在加载中...")
    }

    override fun attachChildLayoutId() = R.layout.activity_login

    override fun createPresenter(): LoginContract.Presenter = LoginPresenter()

    override fun hasBackIcon(): Boolean = false

    override fun initView() {
        super.initView()

        setBaseTitle("登录")
        setBaseTitleColor(R.color.white)
    }


    override fun initData() {
        btn_login.setSingleClickListener {
            val username = username.text.toString()
            val password = password.text.toString()
            mPresenter?.login(username, password)
        }

        btn_get_banner.setSingleClickListener {
            mPresenter?.getBanner()
        }

        btn_logout.setSingleClickListener {
            mPresenter?.logout()
        }

        btn_camera.setSingleClickListener {
            loge("获取相机权限")
            PermissionHelper.requestPermission(this,Manifest.permission.CAMERA,requestSuccess = {
                showSnackMsg("相机权限获取成功")
            })
        }

        btn_storage.setSingleClickListener {
            PermissionHelper.requestPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE){
                showSnackMsg("读取存储权限成功")
            }
        }

    }

    override fun showLoading() {
        mDialog.show()
    }

    override fun hideLoading() {
        mDialog.dismiss()
    }


    override fun start() {
    }

    override fun loginSuccess() {
        showDefaultMsg("登录成功")
    }

    override fun logoutSuccess() {
        showDefaultMsg("退出成功")
    }

    /**
     *  展示获取到得轮播图
     *
     * @param bannerList
     */
    override fun showBanner(bannerList: MutableList<Banner>) {
//        iv_Banner.loadBlurPicture(bannerList[0].imagePath,8)
        iv_Banner.loadReveal(bannerList[0].imagePath)
    }


}
