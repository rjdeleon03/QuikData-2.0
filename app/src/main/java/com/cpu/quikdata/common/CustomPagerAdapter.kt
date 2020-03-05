package com.cpu.quikdata.common

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class CustomPagerAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val mTabs: ArrayList<Fragment> = ArrayList()
    private val mTitles: ArrayList<String> = ArrayList()

    override fun getItem(position: Int): Fragment {
        return mTabs[position]
    }

    override fun getCount(): Int {
        return mTabs.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mTitles[position]
    }

    fun addFragment(fragment: Fragment, title: String) {
        mTabs.add(fragment)
        mTitles.add(title)
    }
}