package com.sx.mvp.starter.lifecycle

import android.app.Activity
import android.app.Application
import android.os.Bundle

/**
 * @author sunxin
 * @date 2020-01-08 11:51
 * @desc Activity 生命周期管理
 */
class ActivityManagerLifecycleCallbackImpl : Application.ActivityLifecycleCallbacks {
    override fun onActivityPaused(activity: Activity) {
    }

    override fun onActivityStarted(activity: Activity) {

    }

    override fun onActivityDestroyed(activity: Activity) {
        ActivityController.removeActivity(activity)
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
    }

    override fun onActivityStopped(activity: Activity) {
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        ActivityController.addActivity(activity)
    }

    override fun onActivityResumed(activity: Activity) {
    }
}