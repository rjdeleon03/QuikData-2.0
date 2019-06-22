package com.cpu.quikdata.feature.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cpu.quikdata.R
import com.cpu.quikdata.common.clickWithGuard
import com.cpu.quikdata.feature.createform.CreateFormActivity
import com.cpu.quikdata.feature.main.MainActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        homeNewDncaFormButton.clickWithGuard {
            CreateFormActivity.newInstance(this)
        }
        homeDncaArchiveButton.clickWithGuard {
            MainActivity.newInstance(this)
        }
    }
}
