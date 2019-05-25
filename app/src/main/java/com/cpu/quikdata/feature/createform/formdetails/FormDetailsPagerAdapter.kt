package com.cpu.quikdata.feature.createform.formdetails

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class FormDetailsPagerAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager) {

    private val mTabs: ArrayList<Fragment> = ArrayList()
    private val mTitles: ArrayList<String> = ArrayList()

    override fun getItem(position: Int): Fragment {
        return mTabs[position]
    }

    override fun getCount(): Int {
        return mTabs.size
    }

    fun addFragment(fragment: Fragment, title: String) {
        mTabs.add(fragment)
        mTitles.add(title)
    }
}