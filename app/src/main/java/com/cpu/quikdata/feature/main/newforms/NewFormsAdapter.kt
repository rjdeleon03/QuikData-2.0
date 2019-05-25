package com.cpu.quikdata.feature.main.newforms

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.cpu.quikdata.R
import com.cpu.quikdata.common.clickWithGuard
import com.cpu.quikdata.data.form.Form
import com.cpu.quikdata.feature.createform.CreateFormActivity
import java.lang.ref.WeakReference

class NewFormsAdapter(context: Context) : RecyclerView.Adapter<NewFormsAdapter.ViewHolder>() {

    private var mContext: WeakReference<Context> = WeakReference(context)
    private var mForms: List<Form>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(mContext.get())
        val view = inflater.inflate(R.layout.item_form, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (mForms == null) 0 else mForms!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val form = mForms!![position]
        holder.setOnClickListener {
            CreateFormActivity.newInstance(mContext.get()!!, form.id, true)
        }
    }

    fun setForms(forms: List<Form>) {
        mForms = forms
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val mView = itemView

        val view: View
            get() = mView

        fun setOnClickListener(listener: () -> Unit) {
            mView.clickWithGuard { listener.invoke() }
        }
    }
}