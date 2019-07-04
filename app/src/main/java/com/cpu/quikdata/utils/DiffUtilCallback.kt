package com.cpu.quikdata.utils

import androidx.recyclerview.widget.DiffUtil

class DiffUtilCallback<T>(oldList: List<T>?, newList: List<T>) : DiffUtil.Callback() {

    var mOldList: List<T>? = oldList
    var mNewList: List<T> = newList

    override fun getOldListSize(): Int = if (mOldList != null) mOldList!!.size else 0

    override fun getNewListSize(): Int = mNewList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = true

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return if (mOldList != null) mOldList!![oldItemPosition] == mNewList[newItemPosition] else false
    }
}