package com.sx.mvp.starter.loading

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.sx.mvp.starter.R
import kotlinx.android.synthetic.main.k_layout_loading.*


class LoadingDialog : DialogFragment() {

    var message = "Loading..."
        set(value) {
            field = value
            mTvTips.text = message
        }

    var dialogTag = "loadingDialog"

    private lateinit var manager: FragmentManager

    companion object {
        fun create(manager: FragmentManager): LoadingDialog {
            return LoadingDialog().also { it.manager = manager }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setCanceledOnTouchOutside(false)

        return inflater.inflate(R.layout.k_layout_loading, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
    }

    private fun initView() {
        mLoading.start()
        mTvTips.text = message
    }

    fun show() {
        this.show(manager, dialogTag)
    }

    fun hide() {
        this.fragmentManager?.findFragmentByTag(dialogTag)?.let {
            this.dismiss()
        }
    }

    override fun onDestroyView() {
        mLoading.stop()
        super.onDestroyView()
    }
}