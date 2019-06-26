package com.cpu.quikdata.feature.main.newforms

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.cpu.quikdata.R
import com.cpu.quikdata.common.clickWithGuard
import com.cpu.quikdata.common.toDateString
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
        val holder = ViewHolder(view) { mDeleteClickListener.invoke(mForms!![it]) }
        holder.setOnClickListener {
            CreateFormActivity.newInstance(mContext.get()!!, mForms!![it].form!!.id, true)
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

    class ViewHolder(itemView: View, listener: (Int) -> Unit) : RecyclerView.ViewHolder(itemView) {

        private val mView = itemView
        private val mDeleteListener = listener

        val view: View
            get() = mView

        init {
            view.formDeleteButton.clickWithGuard {
                val popup = PopupMenu(view.context, view.formDeleteButton)
                popup.menuInflater.inflate(R.menu.form_item_menu, popup.menu)
                popup.setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.menuSubmit -> {}
                        else -> {
                            mDeleteListener.invoke(view.tag as Int)
                        }
                    }
                    return@setOnMenuItemClickListener true
                }
                popup.show()
            }
        }

        fun populateWithData(form: FormComplete, index: Int) {
            view.tag = index
            val formDetails = form.formDetails!![0]
            val baselineData = form.baselineData!![0]
            val calamityInfo = form.calamityInfo!![0]
            view.formItemNameText.text = String.format(view.context.getString(R.string.form_item_assessed), formDetails.assessmentDate.toDateString())

            // region Sitio/Barangay
            if (baselineData.sitio.isBlank() && baselineData.barangay.isBlank()) {
                view.formItemSitioBarangayText.text = view.context.getString(R.string.text_empty_sitio_barangay)
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
                view.formItemCityProvinceText.text = view.context.getString(R.string.text_empty_city_province)
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
    }
}