package com.yicooll.wanandroidkotlin.entity

import java.io.Serializable

data class ModelCollect(
        val `data`: Data,
        val errorCode: Int,
        val errorMsg: String
) : Serializable {
    data class Data(
            val curPage: Int,
            val datas: List<Data>,
            val offset: Int,
            val over: Boolean,
            val pageCount: Int,
            val size: Int,
            val total: Int
    ) : Serializable {
        data class Data(
                val author: String,
                val chapterId: Int,
                val chapterName: String,
                val courseId: Int,
                val desc: String,
                val envelopePic: String,
                val id: Int,
                val link: String,
                val niceDate: String,
                val origin: String,
                val originId: Int,
                val publishTime: Long,
                val title: String,
                val userId: Int,
                val visible: Int,
                val zan: Int
        ) : Serializable
    }
}