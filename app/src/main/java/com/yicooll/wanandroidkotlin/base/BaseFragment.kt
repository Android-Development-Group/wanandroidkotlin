package com.yicooll.wanandroidkotlin.base

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    companion object {
        var TAG: String? = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        TAG = this::class.simpleName
        initView()
        initEvent()
    }

    protected abstract fun initView()
    protected abstract fun initEvent()

    /**
     * toast 消息
     */
    protected fun showToast(msg: String) {
        if (!TextUtils.isEmpty(msg)) {
            Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
        }
    }
}