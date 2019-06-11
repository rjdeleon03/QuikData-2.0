package com.cpu.quikdata.feature.createform.casestories

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cpu.quikdata.R
import com.cpu.quikdata.data.casestories.casestoriesimage.CaseStoriesImageItem
import kotlinx.android.synthetic.main.item_image.view.*

class CaseStoriesImageAdapter(context: Context, deleteClickListener: (CaseStoriesImageItem) -> Unit) :
    RecyclerView.Adapter<CaseStoriesImageAdapter.ViewHolder>() {

    private var mImages: List<CaseStoriesImageItem>? = null
    private val mInflater = LayoutInflater.from(context)
    private val mOnDeleteClickListener = deleteClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.item_image, parent, false)
        val holder = ViewHolder(view)
        holder.setOnDeleteClickListener { mOnDeleteClickListener.invoke(mImages!![it]) }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.populateWithData(mImages!![position], position)
    }

    override fun getItemCount(): Int {
        return if (mImages == null) 0 else mImages!!.size
    }

    fun setImages(images: List<CaseStoriesImageItem>) {
        mImages = images
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val mItemView = itemView

        fun setOnDeleteClickListener(l: (Int) -> Unit) {
            mItemView.setOnLongClickListener {
                l.invoke(mItemView.tag as Int)
                true
            }
        }

        fun populateWithData(imageItem: CaseStoriesImageItem, position: Int) {
            val uri = Uri.parse(imageItem.uri)
            mItemView.tag = position
            mItemView.caseStoriesImageView.setImageURI(uri)
        }
    }
}