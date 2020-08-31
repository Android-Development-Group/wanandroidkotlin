package com.yicooll.wanandroidkotlin.entity

import java.io.Serializable

data class ModelGoodsComment(val userHead: String, val username: String, val comment: String, val star: Float) : Serializable {
}