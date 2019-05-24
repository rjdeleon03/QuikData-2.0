package com.cpu.quikdata.feature.createform

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cpu.quikdata.R

class CreateFormActivity : AppCompatActivity() {

    companion object {
        fun newInstance(context: Context) {
            val intent = Intent(context, CreateFormActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_form)
    }
}
