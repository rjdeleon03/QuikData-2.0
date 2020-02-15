package com.cpu.quikdata.utils

import androidx.recyclerview.widget.DiffUtil

class DiffUtilCallback<T>(oldList: List<T>?, newList: List<T>) : DiffUtil.Callback() {

    var mOldList: List<T>? = oldList
    var mNewList: List<T> = newList

    override fun getOldListSize(): Int {
        mOldList?.let { return it.size }
        return 0
    }

    override fun getNewListSize(): Int = mNewList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = true

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        mOldList?.let { return mOldList!![oldItemPosition] == mNewList[newItemPosition] }
        return false
    }
}