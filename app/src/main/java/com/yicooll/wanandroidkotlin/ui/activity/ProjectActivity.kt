package com.yicooll.wanandroidkotlin.ui.activity

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yicooll.wanandroidkotlin.Constant
import com.yicooll.wanandroidkotlin.EventAction
import com.yicooll.wanandroidkotlin.R
import com.yicooll.wanandroidkotlin.base.BaseActivity
import com.yicooll.wanandroidkotlin.entity.Event
import com.yicooll.wanandroidkotlin.entity.ModelProjectCategory
import com.yicooll.wanandroidkotlin.entity.ModelProjectList
import com.yicooll.wanandroidkotlin.ui.adapter.ProjectAdapter
import com.yicooll.wanandroidkotlin.ui.adapter.ProjectCategoryAdapter
import com.yicooll.wanandroidkotlin.ui.weiget.TypePopupWindow
import com.yicooll.wanandroidkotlin.utils.ToActivityHelper
import com.yicooll.wanandroidkotlin.viewModel.ProjectViewModel
import de.greenrobot.event.EventBus
import kotlinx.android.synthetic.main.activity_project.*
import kotlinx.android.synthetic.main.include_noback_toolbar.*

class ProjectActivity : BaseActivity() {

    private var vm: ProjectViewModel? = null
    private var pageNum = 1
    private var projectList = ArrayList<ModelProjectList.Data.Data>()
    private var projectAdapter: ProjectAdapter? = null
    private var typeId = 0
    private var categoryList = ArrayList<ModelProjectCategory.Data>()
    private var selectPosition = 0

    override fun getContentViewLayoutId(): Int {
        return R.layout.activity_project
    }

    override fun initView() {
        val llMenu = getHeadMenu()
        layoutInflater.inflate(R.layout.include_base_toolbar, llMenu)
        tv_menu_right.visibility = View.VISIBLE
        tv_menu_right.text = "项目"


        projectAdapter = ProjectAdapter(R.layout.adapter_project_list, projectList)
        rv_project.adapter = projectAdapter
        rv_project.layoutManager = LinearLayoutManager(this)

        srv_project.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light)
    }

    private val mHandler = Handler {
        vm?.getProjectCategory()
        srv_project.isRefreshing = false
        return@Handler true
    }

    override fun initEvent() {
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this)

        srv_project.setOnRefreshListener {
            mHandler.sendEmptyMessageDelayed(Constant.FRESH_CODE, Constant.LOADING_DELAYED)
        }

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(ProjectViewModel::class.java)
        vm?.getProjectCategory()
        vm?.getProjectCategoryLiveData()?.observe(this, {
            categoryList.clear()
            it?.let { it1 ->
                if (it1.errorCode == 0) {
                    categoryList.addAll(it1.data)
                    typeId = it1.data[0].id
                    vm?.getProjectByType(typeId, pageNum)
                } else {
                    showToast(it1.errorMsg)
                }
            }
            if (it == null) {
                showToast(Constant.NETWORK_ERROR)
            }
            setProjectList()
        })
        projectAdapter?.setOnLoadMoreListener({
            vm?.getProjectByType(typeId, ++pageNum)
        }, rv_project)

        projectAdapter?.setOnItemClickListener { adapter, view, position ->

            val bundle = Bundle()
            bundle.putString("url", projectList[position].link)
            bundle.putString("title", projectList[position].title)
            ToActivityHelper.getInstance()?.toActivity(this, MainWebActivity::class.java, bundle)

        }

        tv_menu_right.setOnClickListener {
            TypePopupWindow(this@ProjectActivity, ProjectCategoryAdapter(R.layout.adapter_category_item, categoryList), selectPosition)
        }
    }

    private fun setProjectList() {
        vm?.getProjectListLiveData()?.observe(this, {
            if (pageNum == 1) {
                projectList.clear()
            }
            it?.let { it1 ->
                if (it1.errorCode == 0) {
                    projectList.addAll(it1.data.datas)
                    projectAdapter?.notifyDataSetChanged()
                    if (it1.data.datas.size < 15) {
                        projectAdapter?.loadMoreEnd()
                    } else {
                        projectAdapter?.loadMoreComplete()
                    }
                } else {
                    showToast(it1.errorMsg)
                }
            }
            if (it == null) {
                showToast("网络异常")
            }
        })
    }

    override
    fun onEvent(event: Event<Any>) {
        if (event.getAction() == EventAction.PROJECT_CATEGORY) {
            pageNum = 1
            vm?.getProjectByType(categoryList[(event.getData() as Int?)!!].id, pageNum)
            selectPosition = (event.getData() as Int?)!!
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}
