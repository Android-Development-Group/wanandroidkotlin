package com.yicooll.wanandroidkotlin.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yicooll.wanandroidkotlin.entity.ModelIndexArtical
import com.yicooll.wanandroidkotlin.entity.ModelIndexBanner
import com.yicooll.wanandroidkotlin.repository.IndexRepository

class IndexViewModel : ViewModel() {

    private var repository: IndexRepository? = null
    private var indexBannerLiveData: MutableLiveData<ModelIndexBanner>? = null
    private var indexArticalLiveData: MutableLiveData<ModelIndexArtical>? = null

    init {
        repository = IndexRepository()
        indexBannerLiveData = repository?.getBannerLiveData()
        indexArticalLiveData = repository?.getArticalLiveData()
    }

    fun getIndexBanner() {
        repository?.getIndexBanner()
    }

    fun getIndexArtical(pageNum: Int) {
        repository?.getIndexArtical(pageNum)
    }

    fun getBannerLiveData(): MutableLiveData<ModelIndexBanner>? {
        return indexBannerLiveData
    }

    fun getArticalLiveData(): MutableLiveData<ModelIndexArtical>? {
        return indexArticalLiveData
    }

}