package com.yicooll.wanandroidkotlin.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yicooll.wanandroidkotlin.entity.ModelSearch
import com.yicooll.wanandroidkotlin.repository.SearchRepository

class SearchViewModel : ViewModel() {

    private var repository: SearchRepository? = null
    private var searchLiveData: MutableLiveData<ModelSearch>? = null

    init {
        repository = SearchRepository()
        searchLiveData = repository?.getSearchLiveData()
    }

    fun getSearchLiveData(): MutableLiveData<ModelSearch>? {
        return searchLiveData
    }

    fun getSearchData(keyword: String, pageNum: Int) {
        repository?.getSearchData(keyword, pageNum)
    }
}