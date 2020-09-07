package com.yicooll.wanandroidkotlin.ui.activity

import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.tabs.TabLayout
import com.yicooll.wanandroidkotlin.R
import com.yicooll.wanandroidkotlin.base.BaseActivity
import com.yicooll.wanandroidkotlin.ui.fragment.GoodsCommentFragment
import com.yicooll.wanandroidkotlin.ui.fragment.GoodsInfoDetailMainFragment
import com.yicooll.wanandroidkotlin.ui.fragment.GoodsInfoMainFragment
import kotlinx.android.synthetic.main.activity_shop_detail.*

class ShopDetailActivity : BaseActivity() {


    private val fragmentList = ArrayList<Fragment>()
    private val titleArray = arrayOf("商品", "详情", "评价")
    private lateinit var menuTitle: TextView
    private lateinit var tablayout: TabLayout

    override fun getContentViewLayoutId(): Int {
        return R.layout.activity_shop_detail
    }

    override fun initView() {
        val llMenu = getHeadMenu()
        val view = layoutInflater.inflate(R.layout.include_tab_toolbar, llMenu)
        tablayout = view.findViewById(R.id.tab_detial_type)
        menuTitle = view.findViewById(R.id.tv_menu_center)

        fragmentList.clear()
        val mainFragment = GoodsInfoMainFragment()
        fragmentList.add(mainFragment)
        val detialFragment = GoodsInfoDetailMainFragment()
        fragmentList.add(detialFragment)
        val commentFragment = GoodsCommentFragment()
        fragmentList.add(commentFragment)
        viewpager.adapter = VpAdapter(supportFragmentManager)
        tablayout.setupWithViewPager(viewpager)
    }

    override fun initEvent() {

    }

    fun setViewContent(scrollToBottom: Boolean) {
        if (scrollToBottom) {
            viewpager.setNoScroll(true)
            tablayout.visibility = View.GONE
            menuTitle.visibility = View.VISIBLE
            menuTitle.text = "图文详情"
        } else {
            viewpager.setNoScroll(false)
            tablayout.visibility = View.VISIBLE
            menuTitle.visibility = View.GONE
        }
    }

    fun setCurrentPage(position: Int) {
        viewpager.currentItem = position
    }

    inner class VpAdapter(fm: FragmentManager) : androidx.fragment.app.FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        override fun getCount(): Int {
            return fragmentList.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titleArray[position]
        }
    }

}
