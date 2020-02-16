package com.cpu.quikdata.feature.createform

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.cpu.quikdata.R
import com.cpu.quikdata.di.module.viewmodel.ViewModelProviderFactory
import com.cpu.quikdata.common.showConfirmationDialog
import com.cpu.quikdata.feature.createform.casestories.CaseStoriesFragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_create_form.*
import javax.inject.Inject


class CreateFormActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var mViewModelProviderFactory: ViewModelProviderFactory

    private lateinit var mViewModel: CreateFormViewModel
    private lateinit var mNavController: NavController

    private var mLayoutMargin: Int = 0
    private var mEditMode = false

    companion object {
        const val FORM_ID_KEY = "FORM_ID_KEY"
        private const val EDIT_MODE_KEY = "EDIT_MODE_KEY"
        private const val BASIC_MODE_KEY = "BASIC_MODE_KEY"

        @JvmStatic
        fun newInstance(context: Context, formId: String = "", editMode: Boolean = false, basicMode: Boolean = false) {
            val intent = Intent(context, CreateFormActivity::class.java)
            intent.putExtra(FORM_ID_KEY, formId)
            intent.putExtra(EDIT_MODE_KEY, editMode)
            intent.putExtra(BASIC_MODE_KEY, basicMode)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_form)

        mViewModel = mViewModelProviderFactory.create(CreateFormViewModel::class.java)

        setSupportActionBar(createFormToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Get edit mode flag and update toolbar title accordingly
        mEditMode = intent.getBooleanExtra(EDIT_MODE_KEY, false)
        var titleId = R.string.create_form_title_new
        if (mEditMode) {
            titleId = R.string.create_form_title_edit
        }

        // Setup navController
        mNavController = findNavController(R.id.fragment)
        mNavController.addOnDestinationChangedListener { _, destination, _ ->
            toolbarTitle.text = destination.label
            if (destination.id == R.id.selectionFragment) {
                toolbarTitle.setText(titleId)
            }
        }

        // Get basic mode flag and select the correct navigation graph
        val basicMode = intent.getBooleanExtra(BASIC_MODE_KEY, false)
        if (basicMode) {
            mNavController.setGraph(R.navigation.create_form_nav_basic)
        } else {
            mNavController.setGraph(R.navigation.create_form_nav)
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
            mNavController.popBackStack()
        } else {
            if (mViewModel.isFormTemporary) {
                showConfirmationDialog({
                    mViewModel.deleteForm()
                    finish()
                })
            } else {
                Toast.makeText(this, R.string.form_item_changes_saved, Toast.LENGTH_SHORT).show()
                finish()
            }
        }
        return true
    }

    override fun onBackPressed() {
        onSupportNavigateUp()
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
