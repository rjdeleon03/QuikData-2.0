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

class NewFormsAdapter(context: Context, deleteClickListener: (FormComplete) -> Unit) :
    RecyclerView.Adapter<NewFormsAdapter.ViewHolder>() {

    private var mContext: WeakReference<Context> = WeakReference(context)
    private var mForms: List<FormComplete>? = null
    private val mDeleteClickListener = deleteClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(mContext.get())
        val view = inflater.inflate(R.layout.item_form, parent, false)
        val holder = ViewHolder(view)
        holder.setOnClickListener {
            CreateFormActivity.newInstance(mContext.get()!!, mForms!![it].form!!.id, true)
        }
        holder.setOnDeleteClickListener {
            mDeleteClickListener.invoke(mForms!![it])
        }
        return holder
    }

    override fun getItemCount(): Int {
        return if (mForms == null) 0 else mForms!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val form = mForms!![position]
        holder.populateWithData(form, position)
    }

    fun setForms(forms: List<FormComplete>) {
        mForms = forms
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val mView = itemView

        val view: View
            get() = mView

        fun populateWithData(form: FormComplete, index: Int) {
            view.tag = index
            val formDetails = form.formDetails!![0]
            val baselineData = form.baselineData!![0]
            val calamityInfo = form.calamityInfo!![0]
            view.formItemNameText.text = String.format(view.context.getString(R.string.form_item_assessed), formDetails.assessmentDate.toDateString())

            // region Sitio/Barangay
            if (baselineData.sitio.isBlank() && baselineData.barangay.isBlank()) {
                view.formItemSitioBarangayText.visibility = View.GONE
            }
            else if (baselineData.sitio.isBlank() || baselineData.barangay.isBlank()) {
                view.formItemSitioBarangayText.text =
                    String.format(view.context.getString(R.string.text_empty_location), baselineData.sitio, baselineData.barangay)
            } else {
                view.formItemSitioBarangayText.text =
                    String.format(view.context.getString(R.string.text_complete_location), baselineData.sitio, baselineData.barangay)
            }
            // endregion

            // region City/Province
            if (baselineData.city.isBlank() && baselineData.province.isBlank()) {
                view.formItemCityProvinceText.visibility = View.GONE
            }
            else if (baselineData.city.isBlank() || baselineData.province.isBlank()) {
                view.formItemCityProvinceText.text =
                    String.format(view.context.getString(R.string.text_empty_location), baselineData.city, baselineData.province)
            } else {
                view.formItemCityProvinceText.text =
                    String.format(view.context.getString(R.string.text_complete_location), baselineData.city, baselineData.province)
            }
            // endregion

            // region Calamity
            if (!calamityInfo.calamityType.isBlank()) {
                view.formItemCalamityText.text = calamityInfo.calamityType
            }
            // endregion
        }

        fun setOnClickListener(listener: (Int) -> Unit) {
            view.clickWithGuard { if (view.tag != null) listener.invoke(view.tag as Int) }
        }

        fun setOnDeleteClickListener(listener: (Int) -> Unit) {
            view.formDeleteButton.clickWithGuard { if (view.tag != null) listener.invoke(view.tag as Int) }
        }
    }
}