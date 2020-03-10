package com.cpu.quikdata.common.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.cpu.quikdata.R
import kotlinx.android.synthetic.main.item_dropdown_option.view.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class CustomArrayAdapter(context: Context, list: List<Int>) :
    ArrayAdapter<Int>(context, 0, list) {

    private val mInflater = LayoutInflater.from(context)

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createItemView(position, convertView, parent)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createItemView(position, convertView, parent)
    }

    private fun createItemView(position: Int, convertView: View?, parent: ViewGroup): View {

        var view = convertView
        if (convertView == null) {
            view = mInflater.inflate(R.layout.item_dropdown_option, parent, false)
        }

        view!!.dropdownText.text = mInflater.context.resources.getString(getItem(position))
        return view
    }
}