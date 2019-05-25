package com.cpu.quikdata.feature.createform

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class CreateFormViewModel(application: Application) : AndroidViewModel(application) {

    private var mFormId = formId

    var formId: String
        get() = mFormId
        set(value) {
            mFormId = value
        }

}