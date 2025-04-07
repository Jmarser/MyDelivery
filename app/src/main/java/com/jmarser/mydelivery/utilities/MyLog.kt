package com.jmarser.mydelivery.utilities

import android.util.Log
import com.jmarser.mydelivery.BuildConfig

object MyLog {

    private const val TAG = "MyLogger"

    fun d(tag: String = TAG, message: String){
        if (BuildConfig.LOG_ENABLED) {
            Log.d(tag, message)
        }
    }

    fun e(tag: String = TAG, message: String){
        if (BuildConfig.LOG_ENABLED) {
            Log.e(tag, message)
        }
    }

    fun i(tag: String = TAG, message: String){
        if (BuildConfig.LOG_ENABLED) {
            Log.i(tag, message)
        }
    }

    fun v(tag: String = TAG, message: String){
        if (BuildConfig.LOG_ENABLED) {
            Log.v(tag, message)
        }
    }

    fun w(tag: String = TAG, message: String){
        if (BuildConfig.LOG_ENABLED) {
            Log.w(tag, message)
        }
    }

    fun wtf(tag: String = TAG, message: String){
        if (BuildConfig.LOG_ENABLED) {
            Log.wtf(tag, message)
        }
    }

}