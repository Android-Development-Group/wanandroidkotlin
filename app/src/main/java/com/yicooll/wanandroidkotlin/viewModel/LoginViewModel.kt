package com.yicooll.wanandroidkotlin.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yicooll.wanandroidkotlin.entity.ModelLogin
import com.yicooll.wanandroidkotlin.repository.LoginRepository

class LoginViewModel : ViewModel() {

    private var repository: LoginRepository? = null

    fun doLogin(username: String, password: String) {
        repository = LoginRepository(username, password)
    }

    fun getLodinData(): MutableLiveData<ModelLogin>? {

        return repository?.getLoginData()
    }
}