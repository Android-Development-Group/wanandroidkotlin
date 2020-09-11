package com.yicooll.wanandroidkotlin.utils

import android.content.Context

class UserHelper private constructor() {

    companion object {
        val instance: UserHelper by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            UserHelper()
        }
    }

    fun isLogin(context: Context): Boolean {
        return PreferenceHelper.getBoolean(context, "isLogin")
    }

    fun loginOut(context: Context) {
        PreferenceHelper.putBoolean(context, "isLogin", false)
        val cookies = HashSet<String>()
        cookies.clear()
        PreferenceHelper.putStringSet(context, "cookie", cookies)
    }

}