package com.cpu.quikdata.feature.createform.evacuationinfo.base

import com.cpu.quikdata.base.BaseCollapsibleAdapter
import com.cpu.quikdata.base.BaseCollapsibleCreateFormFragment
import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.feature.createform.evacuationinfo.container.EvacuationContainerFragment

abstract class BaseCollapsibleEvacuationItemFragment<A, H>: BaseCollapsibleCreateFormFragment<A, H>()

        where A : BaseCollapsibleAdapter<*, H>, H : BaseCollapsibleAdapter.ViewHolder<*> {

    val mEvacuationItemComponent
        get() = (parentFragment as EvacuationContainerFragment).evacuationItemComponent

}