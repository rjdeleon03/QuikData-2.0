package com.cpu.quikdata.feature.createform.generalinfo.population

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cpu.quikdata.R
import com.cpu.quikdata.common.AgeCategories
import com.cpu.quikdata.data.generalinfo.population.row.PopulationRow
import kotlinx.android.synthetic.main.item_population.view.*
import kotlinx.android.synthetic.main.view_collapsible_container.view.*

class PopulationAdapter(context: Context) : RecyclerView.Adapter<PopulationAdapter.ViewHolder>() {

    private var mInflater = LayoutInflater.from(context)
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
        holder.populateWithData(row!!)
    }

    fun setRows(rows: List<PopulationRow>) {
        mRows = rows
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var mView = itemView

        val view: View
            get() = mView

        fun populateWithData(row: PopulationRow) {
            mView.tag = row.id
            mView.headerTextField.setText(AgeCategories.getStringId(row.type))
            mView.populationAffectedText.number1 = row.affectedMale
            mView.populationAffectedText.number2 = row.affectedFemale
            mView.populationDisplacedText.number1 = row.displacedMale
            mView.populationDisplacedText.number2 = row.displacedFemale
        }

        fun getData(): PopulationRow {
            return PopulationRow(id = mView.tag.toString(),
                affectedMale = mView.populationAffectedText.number1,
                affectedFemale = mView.populationAffectedText.number2,
                displacedMale = mView.populationDisplacedText.number1,
                displacedFemale = mView.populationDisplacedText.number2)
        }
    }
}