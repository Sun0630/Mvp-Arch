package com.sx.mvp.starter.loading

import android.view.View
import com.billy.android.loading.Gloading
import com.sx.mvp.starter.config.AppConfig

/**
 * @author sunxin
 * @date 2020-01-09 10:47
 * @desc
 */
class GlobalAdapter : Gloading.Adapter {
    override fun getView(holder: Gloading.Holder?, convertView: View?, status: Int): View {
        var loadingStatusView:GlobalLoadingStatusView? = null
        if (convertView != null && convertView is GlobalLoadingStatusView){
            loadingStatusView = convertView
        }

        if (loadingStatusView == null) {
            loadingStatusView = GlobalLoadingStatusView(holder?.context,holder?.retryTask)
        }

        loadingStatusView.setStatus(status)
        val data = holder?.getData<Any>()
        val hideMsgView = AppConfig.HIDE_LOADING_STATUS_MSG == data
        loadingStatusView.setMsgViewVisibility(!hideMsgView)
        return loadingStatusView!!
    }
}