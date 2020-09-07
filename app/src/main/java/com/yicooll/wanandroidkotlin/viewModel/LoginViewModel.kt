package com.yicooll.wanandroidkotlin.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.yicooll.wanandroidkotlin.entity.ModelLogin
import com.yicooll.wanandroidkotlin.repository.LoginRepository

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: LoginRepository? = null

    fun doLogin(username: String, password: String) {
        repository = LoginRepository(username, password)
    }

    fun getLodinData(): MutableLiveData<ModelLogin>? {

        return repository?.getLoginData()
    }
}