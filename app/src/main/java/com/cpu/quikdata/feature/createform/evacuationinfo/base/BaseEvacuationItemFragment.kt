package com.cpu.quikdata.feature.createform.evacuationinfo.base

import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.feature.createform.evacuationinfo.container.EvacuationContainerFragment

abstract class BaseEvacuationItemFragment: BaseCreateFormFragment() {

    val mEvacuationItemComponent
        get() = (parentFragment as EvacuationContainerFragment).evacuationItemComponent

}