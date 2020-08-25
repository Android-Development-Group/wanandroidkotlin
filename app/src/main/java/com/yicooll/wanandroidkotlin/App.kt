package com.yicooll.wanandroidkotlin

import android.support.multidex.MultiDexApplication

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private var instance: App? = null
        fun getInstance(): App? {
            return instance
        }
    }
}