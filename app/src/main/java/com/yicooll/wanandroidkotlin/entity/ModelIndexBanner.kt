package com.yicooll.wanandroidkotlin.entity

import java.io.Serializable

data class ModelIndexBanner(
        val `data`: List<Data>,
        val errorCode: Int,
        val errorMsg: String
) : Serializable {
    data class Data(
            val desc: String,
            val id: Int,
            val imagePath: String,
            val isVisible: Int,
            val order: Int,
            val title: String,
            val type: Int,
            val url: String
    ) : Serializable
}