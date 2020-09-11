package com.yicooll.wanandroidkotlin.entity

import java.io.Serializable

data class ModelBase(
        val `data`: Any,
        val errorCode: Int,
        val errorMsg: String
) : Serializable {
}