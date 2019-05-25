package com.cpu.quikdata.feature.createform

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.cpu.quikdata.R
import kotlinx.android.synthetic.main.activity_create_form.*

class CreateFormActivity : AppCompatActivity() {

    private lateinit var mNavController: NavController

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

        setSupportActionBar(createFormToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Get edit mode flag and update toolbar title accordingly
        var titleId = R.string.create_form_title_new
        if (intent.getBooleanExtra(EDIT_MODE_KEY, false)) {
            titleId = R.string.create_form_title_edit
        }

        mNavController = findNavController(R.id.fragment)
        mNavController.addOnDestinationChangedListener { _, destination, _ ->
            toolbarTitle.text = destination.label
            if (destination.id == R.id.selectionFragment) {
                toolbarTitle.setText(titleId)
            }
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onSupportNavigateUp(): Boolean {
        if (fragment.childFragmentManager.backStackEntryCount > 0) {
            fragment.childFragmentManager.popBackStack()
        } else {
            finish()
        }
        return true
    }
}
