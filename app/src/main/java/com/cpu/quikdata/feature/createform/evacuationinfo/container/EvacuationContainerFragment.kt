package com.cpu.quikdata.feature.createform.evacuationinfo.container


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs

import com.cpu.quikdata.R
import com.cpu.quikdata.feature.createform.CreateFormActivity

class EvacuationContainerFragment : Fragment() {

    private val args: EvacuationContainerFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_evacuation_container, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val editMode = args.isEditMode
        if (editMode) {
            (activity as CreateFormActivity).setToolbarTitle(getString(R.string.evacuation_edit_title))
        } else {
            (activity as CreateFormActivity).setToolbarTitle(getString(R.string.evacuation_create_title))
        }
    }
}
