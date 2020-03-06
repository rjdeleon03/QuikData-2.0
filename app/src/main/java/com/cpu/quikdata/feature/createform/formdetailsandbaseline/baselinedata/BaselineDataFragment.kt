package com.cpu.quikdata.feature.createform.formdetailsandbaseline.baselinedata

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.data.baselinedata.BaselineData
import com.cpu.quikdata.feature.createform.activity.CreateFormActivity
import kotlinx.android.synthetic.main.fragment_baseline_data.*

class BaselineDataFragment : BaseCreateFormFragment() {

    companion object {
        @JvmStatic
        fun newInstance() = BaselineDataFragment()
    }

    private val mViewModel: BaselineDataViewModel by lazy {
        ViewModelProvider(this, mViewModelFactory).get(BaselineDataViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mCreateFormComponent.formDetailsAndBaselineComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_baseline_data, container, false)
    }

    override fun onDestroyView() {
        saveData()
        super.onDestroyView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        useBaselineSwitch.setOnCheckedChangeListener { _, isChecked ->
            hideablePrefilledData.visibility = if (isChecked) View.GONE else View.VISIBLE
            if (isChecked) {
                try { saveData() } catch (ex: Exception) {}
                mViewModel.pullPrefilledData()
            }
        }

        mViewModel.baselineData.observe(viewLifecycleOwner, Observer {
            if (it == null) return@Observer
            useBaselineSwitch.isChecked = it.usePrefilled
            organizationText.text = it.organization
            sitioText.text = it.sitio
            barangayText.text = it.barangay
            cityText.text = it.city
            provinceText.text = it.province
            age0to5Text.number1 = it.male_0to5
            age0to5Text.number2 = it.female_0to5
            age6to9Text.number1 = it.male_6to9
            age6to9Text.number2 = it.female_6to9
            age10to12Text.number1 = it.male_10to12
            age10to12Text.number2 = it.female_10to12
            age13to17Text.number1 = it.male_13to17
            age13to17Text.number2 = it.female_13to17
            age18to59Text.number1 = it.male_18to59
            age18to59Text.number2 = it.female_18to59
            age60PlusText.number1 = it.male_60plus
            age60PlusText.number2 = it.female_60plus
            familiesText.number = it.totalFamilies
            householdsText.number = it.totalHouseholds
            concreteText.number = it.shelterConcrete
            semiConcreteText.number = it.shelterSemiConcrete
            lightMaterialsText.number = it.shelterLightMaterials
        })
        mViewModel.prefilledData.observe(viewLifecycleOwner, Observer {
            organizationText.text = it.organization
            sitioText.text = it.sitio
            barangayText.text = it.barangay
            cityText.text = it.city
            provinceText.text = it.province
            age0to5Text.number1 = it.male_0to5
            age0to5Text.number2 = it.female_0to5
            age6to9Text.number1 = it.male_6to9
            age6to9Text.number2 = it.female_6to9
            age10to12Text.number1 = it.male_10to12
            age10to12Text.number2 = it.female_10to12
            age13to17Text.number1 = it.male_13to17
            age13to17Text.number2 = it.female_13to17
            age18to59Text.number1 = it.male_18to59
            age18to59Text.number2 = it.female_18to59
            age60PlusText.number1 = it.male_60plus
            age60PlusText.number2 = it.female_60plus
            familiesText.number = it.totalFamilies
            householdsText.number = it.totalHouseholds
            concreteText.number = it.shelterConcrete
            semiConcreteText.number = it.shelterSemiConcrete
            lightMaterialsText.number = it.shelterLightMaterials
        })
    }

    private fun saveData() {

        /* Save data to model */
        val baselineData = BaselineData(
            usePrefilled = useBaselineSwitch.isChecked,
            organization = organizationText.text,
            sitio = sitioText.text,
            barangay = barangayText.text,
            city = cityText.text,
            province = provinceText.text,
            male_0to5 = age0to5Text.number1,
            female_0to5 = age0to5Text.number2,
            male_6to9 = age6to9Text.number1,
            female_6to9 = age6to9Text.number2,
            male_10to12 = age10to12Text.number1,
            female_10to12 = age10to12Text.number2,
            male_13to17 = age13to17Text.number1,
            female_13to17 = age13to17Text.number2,
            male_18to59 = age18to59Text.number1,
            female_18to59 = age18to59Text.number2,
            male_60plus = age60PlusText.number1,
            female_60plus = age60PlusText.number2,
            totalFamilies = familiesText.number,
            totalHouseholds = householdsText.number,
            shelterConcrete = concreteText.number,
            shelterSemiConcrete = semiConcreteText.number,
            shelterLightMaterials = lightMaterialsText.number
        )
        mViewModel.updateBaselineData(baselineData)
    }

}
