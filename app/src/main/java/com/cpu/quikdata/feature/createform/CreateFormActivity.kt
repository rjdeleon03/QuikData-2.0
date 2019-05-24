package com.cpu.quikdata.feature.createform

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cpu.quikdata.R
import kotlinx.android.synthetic.main.activity_create_form.*

class CreateFormActivity : AppCompatActivity() {

    companion object {
        private const val EDIT_MODE_KEY = "EDIT_MODE_KEY"

        fun newInstance(context: Context, isEditMode: Boolean = false) {
            val intent = Intent(context, CreateFormActivity::class.java)
            intent.putExtra(EDIT_MODE_KEY, isEditMode)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_form)

        // Get edit mode flag and update toolbar title accordingly
        if (intent.getBooleanExtra(EDIT_MODE_KEY, false)) {
            toolbarTitle.setText(R.string.create_form_title_edit)
        }

        setSupportActionBar(createFormToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            finish()
        }
    }
}
