package com.yicooll.wanandroidkotlin.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yicooll.wanandroidkotlin.entity.ModelCollect
import com.yicooll.wanandroidkotlin.repository.CollectRepository

class CollectViewModel : ViewModel() {

    private var collectLiveData: MutableLiveData<ModelCollect>? = null
    private var repository: CollectRepository? = null

    fun getCollectLiveData(): MutableLiveData<ModelCollect>? {
        return collectLiveData
    }

    fun getCollectList(pageNum: Int) {
        repository = CollectRepository()
        repository?.getCollectList(pageNum)
        collectLiveData = repository?.getCollectLiveData()
    }
}