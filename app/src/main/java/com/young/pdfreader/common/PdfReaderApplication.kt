package com.young.pdfreader.common

import android.app.Application
import android.util.Log

/**
 * Create by Young on 11/06/2021
 **/
class PdfReaderApplication : Application() {
    companion object {
        const val TAG = "PdfReaderApplication"
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "App onCreate")
    }

    override fun onTerminate() {
        super.onTerminate()
        Log.d(TAG, "App onTerminate")
    }

    override fun getOpPackageName(): String {
        val packageName = super.getOpPackageName()
        Log.d(TAG, "getOpPackageName() $packageName")
        return packageName
    }

}