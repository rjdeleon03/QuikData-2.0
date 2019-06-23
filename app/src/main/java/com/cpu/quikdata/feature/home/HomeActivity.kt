package com.cpu.quikdata.feature.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.cpu.quikdata.R
import com.cpu.quikdata.common.clickWithGuard
import com.cpu.quikdata.feature.consortium.ConsortiumActivity
import com.cpu.quikdata.feature.createform.CreateFormActivity
import com.cpu.quikdata.feature.main.MainActivity
import com.cpu.quikdata.utils.generateId
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private lateinit var mViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        mViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        homeNewDncaFormButton.clickWithGuard {
            val formId = generateId()
            mViewModel.createNewForm(formId)
            CreateFormActivity.newInstance(this, formId, basicMode = true)
        }
        homeDncaArchiveButton.clickWithGuard {
            MainActivity.newInstance(this)
        }
        homeConsortiumButton.clickWithGuard {
            ConsortiumActivity.newInstance(this)
        }
    }
}
