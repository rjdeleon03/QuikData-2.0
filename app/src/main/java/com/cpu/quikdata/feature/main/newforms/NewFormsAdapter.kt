package com.cpu.quikdata.feature.main.newforms

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cpu.quikdata.R
import com.cpu.quikdata.common.clickWithGuard
import com.cpu.quikdata.common.toDateString
import com.cpu.quikdata.data.form.Form
import com.cpu.quikdata.data.form.FormComplete
import com.cpu.quikdata.feature.createform.CreateFormActivity
import kotlinx.android.synthetic.main.item_form.view.*
import java.lang.ref.WeakReference

class NewFormsAdapter(context: Context) : RecyclerView.Adapter<NewFormsAdapter.ViewHolder>() {

    private var mContext: WeakReference<Context> = WeakReference(context)
    private var mForms: List<FormComplete>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(mContext.get())
        val view = inflater.inflate(R.layout.item_form, parent, false)
        val holder = ViewHolder(view)
        holder.setOnClickListener {
            CreateFormActivity.newInstance(mContext.get()!!, it, true)
        }
        return holder
    }

    override fun getItemCount(): Int {
        return if (mForms == null) 0 else mForms!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val form = mForms!![position]
        holder.populateWithData(form)
    }

    fun setForms(forms: List<FormComplete>) {
        mForms = forms
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val mView = itemView

        val view: View
            get() = mView

        fun populateWithData(form: FormComplete) {
            view.tag = form.form!!.id
            val formDetails = form.formDetails!![0]
            val baselineData = form.baselineData!![0]
            val calamityInfo = form.calamityInfo!![0]
            view.formItemNameText.text = "${view.context.getString(R.string.form_item_assessed)}: ${formDetails.assessmentDate.toDateString()}"
            view.formItemSitioBarangayText.text = "${baselineData.sitio}, ${baselineData.barangay}"
            view.formItemCityProvinceText.text = "${baselineData.city}, ${baselineData.province}"
            view.formItemCalamityText.text = calamityInfo.calamityType
        }

        fun setOnClickListener(listener: (String) -> Unit) {
            view.clickWithGuard { if (view.tag != null) listener.invoke(view.tag as String) }
        }
    }
}