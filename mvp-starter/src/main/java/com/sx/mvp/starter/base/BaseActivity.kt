package com.sx.mvp.starter.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.MotionEvent
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.billy.android.loading.Gloading
import com.sx.mvp.starter.utils.CommonUtil
import com.sx.mvp.starter.utils.KeyBoardUtil
import org.greenrobot.eventbus.EventBus

/**
 * @author sunxin
 * @date 2020-01-07 13:56
 * @desc Activity基类
 */
abstract class BaseActivity : AppCompatActivity() {

    // 是否使用EventBus
    open fun userEventBus(): Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        // 软键盘
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        super.onCreate(savedInstanceState)
        // 强制竖屏
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(attachLayoutId())
        initView()
        initData()
        start()
    }


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        // 当手指抬起来时，如果不是在EditText区域，就关闭软键盘
        if (ev?.action == MotionEvent.ACTION_UP) {
            val v = currentFocus
            if (KeyBoardUtil.isHideKeyboard(v, ev)) {
                KeyBoardUtil.hideKeyBoard(this, v)
            }
        }
        return super.dispatchTouchEvent(ev)
    }


    override fun onDestroy() {
        super.onDestroy()
        if (userEventBus()) {
            EventBus.getDefault().unregister(this)
        }
        CommonUtil.fixInputMethodManagerLeak(this)
    }


    /**
     * 布局文件
     *
     * @return
     */
    @LayoutRes
    protected abstract fun attachLayoutId(): Int

    /**
     * 初始化View
     */
    protected abstract fun initView()

    /**
     * 初始化数据
     */
    open fun initData() {

    }

    /**
     * 开始请求数据
     */
    protected abstract fun start()

}