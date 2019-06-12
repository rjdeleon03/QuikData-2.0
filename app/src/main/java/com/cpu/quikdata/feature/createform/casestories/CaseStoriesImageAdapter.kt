package com.cpu.quikdata.feature.createform.casestories

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.cpu.quikdata.R
import com.cpu.quikdata.common.clickWithGuard
import com.cpu.quikdata.data.casestories.casestoriesimage.CaseStoriesImageItem
import kotlinx.android.synthetic.main.item_image.view.*

class CaseStoriesImageAdapter(context: Context,
                              clickListener: (CaseStoriesImageItem) -> Unit,
                              deleteClickListener: (CaseStoriesImageItem) -> Unit,
                              expandedItem: Int = -1) :
    RecyclerView.Adapter<CaseStoriesImageAdapter.ViewHolder>() {

    private var mImages: List<CaseStoriesImageItem>? = null
    private val mInflater = LayoutInflater.from(context)
    private val mOnClickListener = clickListener
    private val mOnDeleteClickListener = deleteClickListener
    private var mExpandedItem = expandedItem

    val expandedItemIndex: Int
        get() = mExpandedItem

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.item_image, parent, false)
        return ViewHolder(view, {
            mOnClickListener.invoke(mImages!![it]) }, {
            mOnDeleteClickListener.invoke(mImages!![it]) }, { index, isOptionsDisplayed ->
            if (mExpandedItem >= 0) notifyItemChanged(mExpandedItem)
            mExpandedItem = if (isOptionsDisplayed) index else -1
            notifyItemChanged(index)
        })
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.populateWithData(mImages!![position], position, mExpandedItem == position)
    }

    override fun getItemCount(): Int {
        return if (mImages == null) 0 else mImages!!.size
    }

    fun setImages(images: List<CaseStoriesImageItem>) {
        mImages = images
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View, clickListener: (Int) -> Unit, deleteClickListener: (Int) -> Unit,
                     itemUpdatedListener: (Int, Boolean) -> Unit) :
        RecyclerView.ViewHolder(itemView) {

        private val mItemView = itemView

        init {
            mItemView.caseStoriesImageDeleteButton.clickWithGuard {
                deleteClickListener.invoke(mItemView.tag as Int)
            }
            mItemView.caseStoriesImageCancelButton.clickWithGuard {
                mItemView.caseStoriesImageOptions.visibility = View.GONE
                itemUpdatedListener.invoke(mItemView.tag as Int, false)
            }
            mItemView.caseStoriesImageView.clickWithGuard {
                clickListener.invoke(mItemView.tag as Int)
            }
            mItemView.caseStoriesImageView.setOnLongClickListener {
                mItemView.caseStoriesImageOptions.visibility = View.VISIBLE
                itemUpdatedListener.invoke(mItemView.tag as Int, true)
                true
            }
        }

        fun populateWithData(imageItem: CaseStoriesImageItem, position: Int, areOptionsVisible: Boolean) {
            val uri = Uri.parse(imageItem.uri)
            mItemView.tag = position
            Glide.with(mItemView.context)
                .load(uri)
                .placeholder(R.drawable.ic_poll_24dp)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mItemView.caseStoriesImageView)
            mItemView.caseStoriesImageOptions.visibility = if (areOptionsVisible) View.VISIBLE else View.GONE
        }
    }
}