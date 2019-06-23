package com.cpu.quikdata.feature.createform.casestories

import android.Manifest
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager

import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.common.clickWithGuard
import com.cpu.quikdata.common.setupClipping
import com.cpu.quikdata.common.showConfirmationDialog
import com.cpu.quikdata.data.casestories.CaseStories
import com.cpu.quikdata.dialog.InfoDialogFragment
import com.cpu.quikdata.feature.createform.CreateFormActivity
import com.myhexaville.smartimagepicker.ImagePicker
import com.myhexaville.smartimagepicker.OnImagePickedListener
import kotlinx.android.synthetic.main.fragment_case_stories.*
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.OnNeverAskAgain
import permissions.dispatcher.OnPermissionDenied
import permissions.dispatcher.RuntimePermissions

@RuntimePermissions
class CaseStoriesFragment : BaseCreateFormFragment() {

    companion object {
        @JvmStatic
        fun newInstance() = CaseStoriesFragment()
    }

    private lateinit var mViewModel: CaseStoriesViewModel
    private lateinit var mAdapter: CaseStoriesImageAdapter
    private lateinit var mImagePicker: ImagePicker
    private val mExpandedItemKey = "EXPANDED_ITEM_INDEX_KEY"
    private val mItemLimit = 5
    private var mIsItemLimitReached = false
    private var mPreviousItemCount = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_case_stories, container, false)
    }

    override fun onDestroyView() {
        mViewModel.updateCaseStoriesText(CaseStories(text = caseStoriesText.text))
        (activity!! as CreateFormActivity).setSubtitle(getString(R.string.create_form_subtitle))
        super.onDestroyView()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(mExpandedItemKey, mAdapter.expandedItemIndex)
        super.onSaveInstanceState(outState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClipping(view)
        (activity!! as CreateFormActivity).setSubtitle()

        caseStoriesAddImageButton.clickWithGuard {
            if (mIsItemLimitReached) {
                showToastMessage(R.string.case_stories_images_add_limit_error)
                return@clickWithGuard
            }
            startImagePickerWithPermissionCheck()
        }

        var expandedItemIndex = -1
        if (savedInstanceState != null) {
            expandedItemIndex = savedInstanceState.getInt(mExpandedItemKey, 0)
        }

        mAdapter = CaseStoriesImageAdapter(context!!, {
            val action = CaseStoriesFragmentDirections.actionCaseStoriesFragmentToImageViewerFragment(it.uri)
            findNavController().navigate(action)
        }, {
            showConfirmationDialog({
                mViewModel.deleteImage(it)
            }, R.string.case_stories_images_delete_item,
                R.layout.dialog_image_delete,
                R.string.case_stories_images_deleted)
        }, expandedItemIndex)

        caseStoriesRecyclerView.layoutManager = GridLayoutManager(context, 3)
        caseStoriesRecyclerView.adapter = mAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel = ViewModelProviders.of(this, mFactory).get(CaseStoriesViewModel::class.java)
        mViewModel.caseStories.observe(viewLifecycleOwner, Observer {
            caseStoriesText.text = it.root!!.text

            val images = it.images!!
            mAdapter.setImages(images)
            mIsItemLimitReached = images.size >= mItemLimit

            if (mPreviousItemCount != -1 && mPreviousItemCount < images.size) {
                showToastMessage(R.string.case_stories_images_added)
            }
            mPreviousItemCount = images.size
        })

        // Setup image picker
        mImagePicker = ImagePicker(activity!!, this, OnImagePickedListener {
            mViewModel.insertImage(it.toString())
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mImagePicker.handleActivityResult(resultCode, requestCode, data)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }

    @NeedsPermission(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
    fun startImagePicker() {
        mImagePicker.choosePicture(true)
    }

    @OnNeverAskAgain
    @OnPermissionDenied(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
    fun showPermissionDeniedDialog() {
        val dialog = InfoDialogFragment.newInstance(R.string.text_permissions_denied, R.layout.dialog_image_permissions_denied)
        dialog.show(childFragmentManager, InfoDialogFragment.TAG)
    }

    private fun showToastMessage(stringId: Int) {
        Toast.makeText(context!!, stringId, Toast.LENGTH_SHORT).show()
    }
}
