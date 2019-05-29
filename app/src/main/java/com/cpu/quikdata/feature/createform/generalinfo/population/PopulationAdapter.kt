package com.cpu.quikdata.feature.createform.generalinfo.population

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cpu.quikdata.R
import com.cpu.quikdata.common.AgeCategories
import com.cpu.quikdata.customviews.CollapsibleContainer
import com.cpu.quikdata.data.generalinfo.populationrow.PopulationRow
import kotlinx.android.synthetic.main.item_population.view.*
import kotlinx.android.synthetic.main.view_collapsible_container.view.*

class PopulationAdapter(context: Context, rowSaveListener: (PopulationRow) -> Unit) :
    RecyclerView.Adapter<PopulationAdapter.ViewHolder>() {

    private val mInflater = LayoutInflater.from(context)
    private val mRowSaveListener = rowSaveListener
    private var mRows: List<PopulationRow>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.item_population, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (mRows != null) mRows!!.size else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val row = mRows?.get(position)
        holder.populateWithData(row!!, mRowSaveListener)
    }

    fun setRows(rows: List<PopulationRow>) {
        mRows = rows
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var mView = itemView

        val view: View
            get() = mView

        fun populateWithData(row: PopulationRow, rowSaveListener: (PopulationRow) -> Unit) {
            mView.tag = row.id
            mView.headerTextField.setText(AgeCategories.getStringId(row.type))
            mView.populationAffectedText.number1 = row.affectedMale
            mView.populationAffectedText.number2 = row.affectedFemale
            mView.populationDisplacedText.number1 = row.displacedMale
            mView.populationDisplacedText.number2 = row.displacedFemale

            // Setup listener for saving each population row
            (mView as CollapsibleContainer).onDetachedListener = {
                rowSaveListener(
                    PopulationRow(
                        row.id,
                        row.type,
                        mView.populationAffectedText.number1,
                        mView.populationAffectedText.number2,
                        mView.populationDisplacedText.number1,
                        mView.populationDisplacedText.number2,
                        row.formId
                    )
                )
            }

        }
    }
}