package com.yicooll.wanandroidkotlin.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yicooll.wanandroidkotlin.entity.ModelProjectCategory
import com.yicooll.wanandroidkotlin.entity.ModelProjectList
import com.yicooll.wanandroidkotlin.repository.ProjectRepository

class ProjectViewModel : ViewModel() {

    private var repository: ProjectRepository? = null
    private var projectListLiveData: MutableLiveData<ModelProjectList>? = null
    private var projectCategoryLiveData: MutableLiveData<ModelProjectCategory>? = null

    fun getProjectListLiveData(): MutableLiveData<ModelProjectList>? {
        return projectListLiveData
    }

    fun getProjectCategoryLiveData(): MutableLiveData<ModelProjectCategory>? {
        return projectCategoryLiveData
    }

    fun getProjectByType(cid: Int, pageNum: Int) {
        repository?.getProjectByType(cid, pageNum)
        projectListLiveData = repository?.getProjectListLiveData()
    }

    fun getProjectCategory() {
        repository = ProjectRepository()
        repository?.getProjectCategory()
        projectCategoryLiveData = repository?.getProjectCategoryLiveData()
    }
}