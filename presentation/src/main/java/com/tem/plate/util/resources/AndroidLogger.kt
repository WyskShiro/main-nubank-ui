package com.tem.plate.util.resources

import android.content.Context
import android.util.Log
import com.tem.domain.util.Logger
import com.tem.plate.BuildConfig.DEBUG
import com.tem.plate.R
import javax.inject.Inject

class AndroidLogger @Inject constructor(context: Context) : Logger {

    private val tag = context.getString(R.string.app_name)

    override fun v(message: String) {
        if (DEBUG) Log.v(tag, message)
    }

    override fun v(message: String, tr: Throwable) {
        if (DEBUG) Log.v(tag, message, tr)
    }

    override fun d(message: String) {
        if (DEBUG) Log.d(tag, message)
    }

    override fun d(message: String, tr: Throwable) {
        if (DEBUG) Log.d(tag, message, tr)
    }

    override fun i(message: String) {
        if (DEBUG) Log.i(tag, message)
    }

    override fun i(message: String, tr: Throwable) {
        if (DEBUG) Log.i(tag, message, tr)
    }

    override fun w(message: String) {
        if (DEBUG) Log.w(tag, message)
    }

    override fun w(message: String, tr: Throwable) {
        if (DEBUG) Log.w(tag, message, tr)
    }

    override fun w(tr: Throwable) {
        if (DEBUG) Log.w(tag, tr.message, tr)
    }

    override fun e(message: String) {
        if (DEBUG) Log.e(tag, message)
    }

    override fun e(message: String, tr: Throwable) {
        if (DEBUG) Log.e(tag, message, tr)
    }

    override fun e(tr: Throwable) {
        if (DEBUG)
            Log.e(tag, tr.message, tr)
    }
}
