package com.yicooll.wanandroidkotlin.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yicooll.wanandroidkotlin.entity.ModelGoodsComment
import com.yicooll.wanandroidkotlin.entity.ModelGoodsInfo
import com.yicooll.wanandroidkotlin.repository.ShopDetialRepository

class ShopDetialViewModel : ViewModel() {

    private var repository: ShopDetialRepository? = null
    private var goodsInfoLiveData = MutableLiveData<ModelGoodsInfo>()
    private var recommendLiveData = MutableLiveData<List<List<ModelGoodsInfo>>>()
    private var commentLiveData = MutableLiveData<List<ModelGoodsComment>>()

    init {
        repository = ShopDetialRepository()
        repository?.getCommentList()
        repository?.getRecommendList()
        repository?.getGoodsInfo()
        goodsInfoLiveData = repository?.getGoodsInfoLiveData()!!
        recommendLiveData = repository?.getRecommendLiveData()!!
        commentLiveData = repository?.getCommentLiveData()!!
    }

    fun getCommentLiveData(): MutableLiveData<List<ModelGoodsComment>> {
        return commentLiveData
    }

    fun getRecommendLiveData(): MutableLiveData<List<List<ModelGoodsInfo>>> {
        return recommendLiveData
    }

    fun getGoodsInfoLiveData(): MutableLiveData<ModelGoodsInfo> {
        return goodsInfoLiveData
    }
}