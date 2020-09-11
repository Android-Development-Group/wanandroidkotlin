package com.yicooll.wanandroidkotlin.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yicooll.wanandroidkotlin.entity.ModelBase
import com.yicooll.wanandroidkotlin.entity.ModelLogin
import com.yicooll.wanandroidkotlin.repository.LoginRepository

class LoginViewModel : ViewModel() {

    private var repository = LoginRepository()
    private var liveLoginData: MutableLiveData<ModelLogin>? = null
    private var liveRegisterData: MutableLiveData<ModelBase>? = null

    init {
        liveLoginData = repository.getLoginData()
        liveRegisterData = repository.getRegisterData()
    }

    fun doLoginOoRegister(isLogin: Boolean, username: String, password: String) {
        if (isLogin) {
            repository.doLogin(username, password)
        } else {
            repository.doRegister(username, password, password)
        }
    }

    fun getLodinData(): MutableLiveData<ModelLogin>? {
        return liveLoginData
    }

    fun getRegisterData(): MutableLiveData<ModelBase>? {
        return liveRegisterData
    }
}