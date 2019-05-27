package com.cpu.quikdata.feature.createform.generalinfo.population

import android.graphics.Rect
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.core.view.children
import androidx.lifecycle.Observer

import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseFocusableFragment
import com.cpu.quikdata.common.ViewModelFactory
import com.cpu.quikdata.customviews.CollapsibleContainer
import com.cpu.quikdata.feature.createform.CreateFormViewModel
import kotlinx.android.synthetic.main.fragment_population.*
import kotlin.math.roundToInt
import android.view.ViewTreeObserver
import androidx.core.view.doOnNextLayout


class PopulationFragment : BaseFocusableFragment() {

    companion object {
        private const val CLICK_ACTION_THRESHOLD = 200
        fun newInstance() = PopulationFragment()
    }

    private lateinit var mParentViewModel: CreateFormViewModel
    private lateinit var mViewModel: PopulationViewModel
    private lateinit var mAdapter: PopulationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_population, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter = PopulationAdapter(context!!)
        populationRecyclerView.adapter = mAdapter

        var startX = 0F
        var startY = 0F
        populationRecyclerView.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    startX = event.x
                    startY = event.y
                }
                MotionEvent.ACTION_UP -> {
                    if (isClickAction(startX, startY, event.x, event.y)) {
                        populationRecyclerView.children.forEach {
                            if (it is CollapsibleContainer) {
                                val viewRect = Rect()
                                it.getHitRect(viewRect)
                                if (viewRect.contains(event.x.roundToInt(), event.y.roundToInt())) {
                                    it.expand()
                                } else {
                                    it.collapse()
                                }
                            }
                        }
                    }
                }
            }
            false
        }
    }

    private fun isClickAction(beforeX: Float, beforeY: Float, afterX: Float, afterY: Float): Boolean{
        val tolerance = ViewConfiguration.get(context!!).scaledTouchSlop
        return Math.abs(afterX - beforeX) <= tolerance && Math.abs(afterY - beforeY) <= tolerance
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mParentViewModel = ViewModelProviders.of(activity!!).get(CreateFormViewModel::class.java)

        val factory = ViewModelFactory(activity!!.application, mParentViewModel.formId)
        var isInit = true
        mViewModel = ViewModelProviders.of(this, factory).get(PopulationViewModel::class.java)
        mViewModel.population.observe(viewLifecycleOwner, Observer {
            populationRecyclerView.doOnNextLayout {
                if (isInit) {
                    (populationRecyclerView.getChildAt(0) as CollapsibleContainer).expand(false)
                }
                isInit = false
            }
            if (it.populationRows != null) mAdapter.setRows(it.populationRows!!)
        })
    }

}
