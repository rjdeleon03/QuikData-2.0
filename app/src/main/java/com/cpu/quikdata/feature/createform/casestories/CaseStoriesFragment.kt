package com.cpu.quikdata.feature.createform.casestories

import android.Manifest
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager

import com.cpu.quikdata.R
import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.common.clickWithGuard
import com.cpu.quikdata.data.casestories.CaseStories
import com.cpu.quikdata.dialog.InfoDialogFragment
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
        fun newInstance() = CaseStoriesFragment()
    }

    private lateinit var mViewModel: CaseStoriesViewModel
    private lateinit var mAdapter: CaseStoriesImageAdapter
    private lateinit var mImagePicker: ImagePicker

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_case_stories, container, false)
    }

    override fun onDestroyView() {
        mViewModel.updateCaseStoriesText(CaseStories(text = caseStoriesText.text))
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        caseStoriesAddImageButton.clickWithGuard {
            startImagePickerWithPermissionCheck()
        }
        mAdapter = CaseStoriesImageAdapter(context!!) {
            System.out.println("===========> Deleting image...")
        }
        caseStoriesRecyclerView.layoutManager = GridLayoutManager(context, 3)
        caseStoriesRecyclerView.adapter = mAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel = ViewModelProviders.of(this, mFactory).get(CaseStoriesViewModel::class.java)
        mViewModel.caseStories.observe(viewLifecycleOwner, Observer {
            caseStoriesText.text = it.root!!.text
            mAdapter.setImages(it.images!!)
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
}
