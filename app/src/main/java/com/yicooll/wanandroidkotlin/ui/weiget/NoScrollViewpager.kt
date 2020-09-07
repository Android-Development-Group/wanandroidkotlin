package com.yicooll.wanandroidkotlin.ui.weiget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class NoScrollViewpager(context: Context, attrs: AttributeSet) : ViewPager(context, attrs) {

    private var noScroll = false

    fun setNoScroll(noScroll: Boolean) {
        this.noScroll = noScroll
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return !noScroll && super.onInterceptTouchEvent(ev)
    }


    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return !noScroll && super.onTouchEvent(ev)
    }
}