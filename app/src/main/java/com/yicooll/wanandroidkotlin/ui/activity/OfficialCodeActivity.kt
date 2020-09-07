package com.yicooll.wanandroidkotlin.ui.activity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.yicooll.wanandroidkotlin.Constant
import com.yicooll.wanandroidkotlin.R
import com.yicooll.wanandroidkotlin.base.BaseActivity
import com.yicooll.wanandroidkotlin.entity.ModelOfficialCodeCategory
import com.yicooll.wanandroidkotlin.ui.fragment.OfficialCodeListFragment
import com.yicooll.wanandroidkotlin.viewModel.OfficialCodeViewModel
import kotlinx.android.synthetic.main.activity_official_code.*
import kotlinx.android.synthetic.main.include_noback_toolbar.*

class OfficialCodeActivity : BaseActivity() {


    private var vm: OfficialCodeViewModel? = null
    private var officialCodeCategory = ArrayList<ModelOfficialCodeCategory.Data>()
    private var fragmentList = ArrayList<Fragment>()
    private var adapter: FragmentPagerAdapter? = null

    override fun getContentViewLayoutId(): Int {
        return R.layout.activity_official_code
    }

    override fun initView() {
        val llMenu = getHeadMenu()
        layoutInflater.inflate(R.layout.include_base_toolbar, llMenu)
        tv_menu_center.text = "公众号"
        adapter = FragmentPagerAdapter(supportFragmentManager)
        viewpager.adapter = adapter
        tab_layout.setupWithViewPager(viewpager)

    }

    override fun initEvent() {
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(OfficialCodeViewModel::class.java)
        vm?.getOfficialCodeCategory()
        vm?.getOfficialCodeCategoryLiveData()?.observe(this, {
            it?.let { it1 ->
                if (it1.errorCode == 0) {
                    officialCodeCategory.clear()
                    fragmentList.clear()
                    officialCodeCategory.addAll(it1.data)
                    for (index in it1.data.indices) {
                        val fragment = OfficialCodeListFragment.newInstance(it1.data[index].id)
                        fragmentList.add(fragment)
                    }
                    adapter?.notifyDataSetChanged()
                } else {
                    showToast(it1.errorMsg)
                }
            }
            if (it == null) {
                showToast(Constant.NETWORK_ERROR)
            }
        })
    }

    inner class FragmentPagerAdapter(fm: FragmentManager) : androidx.fragment.app.FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        override fun getCount(): Int {
            return fragmentList.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return officialCodeCategory[position].name
        }
    }
}
