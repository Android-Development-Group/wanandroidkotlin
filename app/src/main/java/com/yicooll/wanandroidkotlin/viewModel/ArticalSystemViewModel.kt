package com.yicooll.wanandroidkotlin.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yicooll.wanandroidkotlin.entity.ModelSystemCatogry
import com.yicooll.wanandroidkotlin.repository.ArticalSystemRepository

class ArticalSystemViewModel : ViewModel() {

    var repository: ArticalSystemRepository? = null
    var systemCatogoryLiveData: MutableLiveData<ModelSystemCatogry>? = null

    init {
        repository = ArticalSystemRepository()
        systemCatogoryLiveData = repository?.getSystemCatogryLiveData()
    }

    fun getArticalSystemCatogry() {
        repository?.getArticalSystemCatogry()
    }

    fun getSystemCatogryLiveData(): MutableLiveData<ModelSystemCatogry>? {
        return systemCatogoryLiveData
    }

}