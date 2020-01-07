package com.sx.mvp.arch.utils

import android.annotation.SuppressLint
import android.content.Context
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * @author sunxin
 * @date 2020-01-07 16:21
 * @desc 对话框
 */
object DialogUtil {

    /**
     * 提示Dialog
     *
     * @param context
     * @param message
     * @return
     */
    fun getTipDialog(context: Context, message: String): QMUITipDialog {
        return QMUITipDialog.Builder(context).setTipWord(message).create()
    }


    /**
     * 展示提示，并在 1.5s 后自动关闭
     *
     * @param context
     * @param message
     * @return
     */
    @SuppressLint("CheckResult")
    fun showTipDialog(context: Context, message: String): QMUITipDialog {
        val tipDialog = getTipDialog(context, message)
        tipDialog.show()
        Observable.timer(1500, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ tipDialog.dismiss() }, { })
        return tipDialog
    }

    /**
     * 获取加载中的 Dialog
     *
     * @param context
     * @param message
     * @return
     */
    fun getWaitDialog(context: Context, message: String): QMUITipDialog {
        return QMUITipDialog.Builder(context)
            .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
            .setTipWord(message)
            .create()
    }
}