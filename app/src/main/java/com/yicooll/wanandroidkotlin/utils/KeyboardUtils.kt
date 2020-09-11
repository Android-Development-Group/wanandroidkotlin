package com.yicooll.wanandroidkotlin.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import java.util.*

/**
 * Copyright (C) 2014 Guangzhou QTONE Technologies Ltd.
 *
 *
 * 本代码版权归广州全通教育股份有限公司所有，且受到相关的法律保护。没有经过版权所有者的书面同意，
 * 任何其他个人或组织均不得以任何形式将本文件或本文件的部分代码用于其他商业用途。
 *
 * @version V1.0
 * @date 2014年10月15日 下午6:06:28
 */
object KeyboardUtils {
    /**
     * Hide keyboard
     */
    fun closeKeyboard(activity: Activity) {
        try {
            val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                    ?: return
            var view = activity.currentFocus
            if (view == null) view = View(activity)
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * Show keyboard
     */
    fun showkeyboard(edit: EditText, activity: Activity) {
        try {
            val timer = Timer()
            timer.schedule(object : TimerTask() {
                override fun run() {
                    try {
                        val m = edit.context.getSystemService(
                                Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        m.showSoftInputFromInputMethod(activity.currentFocus!!.windowToken,
                                InputMethodManager.SHOW_FORCED)
                        m.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }, 500)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}