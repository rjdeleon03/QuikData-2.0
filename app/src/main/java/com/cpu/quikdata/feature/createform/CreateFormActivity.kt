package com.cpu.quikdata.feature.createform

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.cpu.quikdata.R
import com.cpu.quikdata.common.ViewModelFactory
import com.cpu.quikdata.feature.createform.casestories.CaseStoriesFragment
import kotlinx.android.synthetic.main.activity_create_form.*


class CreateFormActivity : AppCompatActivity() {

    private lateinit var mNavController: NavController
    private lateinit var mViewModel: CreateFormViewModel
    private var mLayoutMargin: Int = 0

    companion object {
        private const val FORM_ID_KEY = "FORM_ID_KEY"
        private const val EDIT_MODE_KEY = "EDIT_MODE_KEY"

        @JvmStatic
        fun newInstance(context: Context, formId: String = "", editMode: Boolean = false) {
            val intent = Intent(context, CreateFormActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
            intent.putExtra(FORM_ID_KEY, formId)
            intent.putExtra(EDIT_MODE_KEY, editMode)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_form)

        setSupportActionBar(createFormToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Get edit mode flag and update toolbar title accordingly
        val editMode = intent.getBooleanExtra(EDIT_MODE_KEY, false)
        var titleId = R.string.create_form_title_new
        if (editMode) {
            titleId = R.string.create_form_title_edit
        }

        // Initialize viewModel with form ID
        val formId = intent.getStringExtra(FORM_ID_KEY)
        if (!formId.isNullOrEmpty()) {
            val factory = ViewModelFactory(application, formId)
            mViewModel = ViewModelProviders.of(this, factory).get(CreateFormViewModel::class.java)
        }

        // Setup navController
        mNavController = findNavController(R.id.fragment)
        mNavController.addOnDestinationChangedListener { _, destination, _ ->
            toolbarTitle.text = destination.label
            if (destination.id == R.id.selectionFragment) {
                toolbarTitle.setText(titleId)
            }
        }


        val ta = theme.obtainStyledAttributes(
            intArrayOf(android.R.attr.actionBarSize)
        )
        mLayoutMargin = ta.getDimension(0, 0f).toInt()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        fragment.childFragmentManager.fragments.forEach { f ->
            if (f is CaseStoriesFragment) {
                f.onActivityResult(requestCode, resultCode, data)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        if (fragment.childFragmentManager.backStackEntryCount > 0) {
            fragment.childFragmentManager.popBackStack()
        } else {
            finish()
        }
        return true
    }

    fun setToolbarTitle(title: CharSequence? = "") {
        toolbarTitle.text = title
        if (!title.isNullOrBlank()) {
            toolbarTitle.visibility = View.VISIBLE
        } else {
            toolbarTitle.visibility = View.GONE
        }
    }

    fun setSubtitle(subtitle: CharSequence? = "") {
        toolbarSubtitle.text = subtitle
        if (!subtitle.isNullOrBlank()) {
            toolbarSubtitle.visibility = View.VISIBLE
        } else {
            toolbarSubtitle.visibility = View.GONE
        }
    }

    fun hideToolbar() {
        createFormToolbar?.visibility = View.GONE
        val params = createFormFragmentLayout.layoutParams as RelativeLayout.LayoutParams
        params.topMargin = 0
        createFormFragmentLayout.layoutParams = params
    }

    fun showToolbar() {
        createFormToolbar?.visibility = View.VISIBLE
        val params = createFormFragmentLayout.layoutParams as RelativeLayout.LayoutParams
        params.topMargin = mLayoutMargin
        createFormFragmentLayout.layoutParams = params
    }
}
