package com.cpu.quikdata.feature.emergency

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cpu.quikdata.R
import com.cpu.quikdata.common.setupClipping
import kotlinx.android.synthetic.main.activity_send_emergency.*

class SendEmergencyActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, SendEmergencyActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_emergency)

        setupClipping(sendEmergencyFragmentLayout)

        setSupportActionBar(sendEmergencyToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
