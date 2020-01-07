package com.sx.mvp.starter.base

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import com.sx.mvp.starter.R
import com.sx.mvp.starter.mvp.IPresenter
import com.sx.mvp.starter.mvp.IView
import kotlinx.android.synthetic.main.activity_base_title.*
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
 * @author sunxin
 * @date 2020-01-07 14:30
 * @desc 带头部导航的基类
 */
abstract class BaseMvpTitleActivity<in V : IView, P : IPresenter<V>> : BaseMvpActivity<V, P>() {

    protected abstract fun attachChildLayoutId():Int

    override fun attachLayoutId(): Int = R.layout.activity_base_title

    /**
     * 是否有返回键
     *
     * @return
     */
    open fun hasBackIcon(): Boolean = true


    override fun initView() {
        super.initView()
        fl_container.addView(layoutInflater.inflate(attachChildLayoutId(), null, false))
        toolbar.title = ""
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(hasBackIcon())
    }


    fun setBaseTitle(title: String) {
        tv_base_title.text = title
    }

    fun setBaseTitleRes(@StringRes resId: Int) {
        tv_base_title.setText(resId)
    }

    fun setBaseTitleColor(@ColorRes color: Int) {
        tv_base_title.setTextColor(resources.getColor(color))
    }

}