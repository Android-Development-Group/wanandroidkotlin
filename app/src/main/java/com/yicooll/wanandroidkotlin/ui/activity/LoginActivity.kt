package com.yicooll.wanandroidkotlin.ui.activity


import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.KeyboardUtils
import com.yicooll.wanandroidkotlin.R
import com.yicooll.wanandroidkotlin.base.BaseActivity
import com.yicooll.wanandroidkotlin.utils.PreferenceHelper
import com.yicooll.wanandroidkotlin.viewModel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : BaseActivity() {

    private var vm: LoginViewModel? = null
    private var isLogin: Boolean = false

    override fun getContentViewLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun initView() {
        val llMenu: LinearLayout? = getHeadMenu()
        val view: View = layoutInflater.inflate(R.layout.include_base_toolbar, llMenu)
        val tvTitle: TextView = view.findViewById(R.id.tv_menu_center)
        tvTitle.text = "登录"

        tv_login.observer(et_usernmae, et_password)
    }

    override fun initEvent() {
        tv_login.setOnClickListener {
            isLogin = true
            invalidateInfo()
        }
        tv_register.setOnClickListener {
            isLogin = false
            invalidateInfo()
        }

        vm = ViewModelProvider.NewInstanceFactory().create(LoginViewModel::class.java)
        vm?.getLodinData()?.observe(this, {
//            Log.i(TAG, "getLodinData: $it")
            it?.let { it1 ->
                if (it1.errorCode == 0) {
                    loginOrRegisterSuccess(it1)
                } else {
                    showToast(it1.errorMsg)
                }
            }
            if (it == null) {
                showToast("网络异常")
            }
        })
        vm?.getRegisterData()?.observe(this, {
//            Log.i(TAG, "getLodinData: $it")
            it?.let() { it1 ->
                if (it1.errorCode == 0) {
                    loginOrRegisterSuccess(it1)
                } else {
                    showToast(it1.errorMsg)
                }
            }
            if (it == null) {
                showToast("网络异常")
            }
        })
    }

    private fun loginOrRegisterSuccess(it: Any) {
        showToast(if (isLogin) "登录成功" else "注册成功")
        if (isLogin) {
            PreferenceHelper.putBoolean(this, "isLogin", true)
            finish()
        }
    }

    private fun invalidateInfo() {
        KeyboardUtils.hideSoftInput(this)
        val usernmae = et_usernmae.text.toString().trim()
        val password = et_password.text.toString().trim()
        if (usernmae.isEmpty()) {
            showToast("请输入用户名")
            return
        }
        if (password.isEmpty()) {
            showToast("请输入密码")
            return
        }
        if (password.length < 6) {
            showToast("请输入6位以上密码")
            return
        }
        vm?.doLoginOoRegister(isLogin, usernmae, password)
    }
}
