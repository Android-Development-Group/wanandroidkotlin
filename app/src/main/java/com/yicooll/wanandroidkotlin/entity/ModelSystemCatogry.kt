package com.yicooll.wanandroidkotlin.entity

import java.io.Serializable

data class ModelSystemCatogry(
        val `data`: List<Data>,
        val errorCode: Int,
        val errorMsg: String
) : Serializable {
    data class Data(
            val children: List<Children>,
            val courseId: Int,
            val id: Int,
            val name: String,
            val order: Int,
            val parentChapterId: Int,
            val userControlSetTop: Boolean,
            val visible: Int
    ) : Serializable {
        data class Children(
                val children: List<Any>,
                val courseId: Int,
                val id: Int,
                val name: String,
                val order: Int,
                val parentChapterId: Int,
                val userControlSetTop: Boolean,
                val visible: Int
        ) : Serializable
    }
}