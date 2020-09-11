package com.yicooll.wanandroidkotlin.repository

import androidx.lifecycle.MutableLiveData
import com.yicooll.wanandroidkotlin.api_service.UserService
import com.yicooll.wanandroidkotlin.entity.ModelBase
import com.yicooll.wanandroidkotlin.entity.ModelLogin
import com.yicooll.wanandroidkotlin.network.RetrofitUtil
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class LoginRepository {

    private val liveLoginData = MutableLiveData<ModelLogin>()
    private val liveRegisterData = MutableLiveData<ModelBase>()

    fun getLoginData(): MutableLiveData<ModelLogin> {
        return liveLoginData
    }

    fun getRegisterData(): MutableLiveData<ModelBase> {
        return liveRegisterData
    }

    fun doLogin(username: String, password: String) {
        val client = RetrofitUtil.instance
        val service = client.create(UserService::class.java)
        service.doLogin(username, password)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<ModelLogin> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(value: ModelLogin) {
                        liveLoginData.value = value
                    }

                    override fun onError(e: Throwable) {
                        liveLoginData.value = null
                    }
                })
    }

    fun doRegister(username: String, password: String, repassword: String) {
        val client = RetrofitUtil.instance
        val service = client.create(UserService::class.java)
        service.doRegister(username, password, repassword)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<ModelBase> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(value: ModelBase) {
                        liveRegisterData.value = value
                    }

                    override fun onError(e: Throwable) {
                        liveRegisterData.value = null
                    }
                })
    }
}