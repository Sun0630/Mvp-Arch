package com.sx.mvp.starter.provider

import android.app.Application
import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import com.sx.mvp.starter.lifecycle.ActivityManagerLifecycleCallbackImpl

/**
 * 在ContentProvider中注册生命周期
 *
 */
class KotlinMvpContentProvider : ContentProvider() {
    companion object {
        lateinit var application: Application
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        return null
    }

    override fun onCreate(): Boolean {
        application = context!!.applicationContext as Application
        application.registerActivityLifecycleCallbacks(ActivityManagerLifecycleCallbackImpl())
        return true
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return 0
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return 0
    }

    override fun getType(uri: Uri): String? {
        return ""
    }
}