package com.cpu.quikdata.feature.imageviewer


import android.Manifest
import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

import com.cpu.quikdata.R
import com.cpu.quikdata.feature.createform.CreateFormActivity
import kotlinx.android.synthetic.main.fragment_image_viewer.*
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.RuntimePermissions

@RuntimePermissions
class ImageViewerFragment : Fragment() {

    private val args: ImageViewerFragmentArgs by navArgs()

    companion object {

        @JvmStatic
        fun newInstance() = ImageViewerFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image_viewer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageUri = args.imageUri
        if (imageUri.isNotBlank()) {
            loadImageFromUriWithPermissionCheck(imageUri)
        }
    }

    @NeedsPermission(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
    fun loadImageFromUri(imageUri: String) {
        val uri = Uri.parse(imageUri)
        Glide.with(context!!)
            .load(uri)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imageViewerView)
    }

    override fun onResume() {
        super.onResume()
        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        if (activity != null && activity is CreateFormActivity) {
            (activity as CreateFormActivity).hideToolbar()
        }
    }

    override fun onPause() {
        if (activity != null && activity is CreateFormActivity) {
            (activity as CreateFormActivity).showToolbar()
        }
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        super.onPause()
    }
}
