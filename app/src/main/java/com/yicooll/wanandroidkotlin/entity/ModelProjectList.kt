package com.yicooll.wanandroidkotlin.entity

import java.io.Serializable

data class ModelProjectList(
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
                val apkLink: String,
                val author: String,
                val chapterId: Int,
                val chapterName: String,
                val collect: Boolean,
                val courseId: Int,
                val desc: String,
                val envelopePic: String,
                val fresh: Boolean,
                val id: Int,
                val link: String,
                val niceDate: String,
                val origin: String,
                val prefix: String,
                val projectLink: String,
                val publishTime: Long,
                val superChapterId: Int,
                val superChapterName: String,
                val tags: List<Tag>,
                val title: String,
                val type: Int,
                val userId: Int,
                val visible: Int,
                val zan: Int
        ) : Serializable {
            data class Tag(
                    val name: String,
                    val url: String
            ) : Serializable
        }
    }
}