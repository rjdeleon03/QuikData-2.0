package com.cpu.quikdata.dialog


import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment

import com.cpu.quikdata.R

/**
 * A simple [Fragment] subclass.
 *
 */
class ProgressDialogFragment : DialogFragment() {

    companion object {
        const val TAG = "PROGRESS_DIALOG_FRAGMENT"
        private const val TITLE_ID_KEY = "TITLE_ID_KEY"
        private const val CONTENT_ID_KEY = "CONTENT_ID_KEY"

        @JvmStatic
        fun newInstance(titleId: Int = -1, textId: Int) : ProgressDialogFragment {
            val fragment = ProgressDialogFragment()
            val bundle = Bundle()
            bundle.putInt(TITLE_ID_KEY, titleId)
            bundle.putInt(CONTENT_ID_KEY, textId)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val titleId = arguments?.getInt(TITLE_ID_KEY, -1)
        val willUseTitle = titleId != -1

        val contentId = arguments?.getInt(CONTENT_ID_KEY)
        val content = LayoutInflater.from(context!!).inflate(contentId!!, null)

        val builder = AlertDialog.Builder(context!!)
        builder
            .setView(content)
            .setPositiveButton(getString(R.string.text_cancel)) { _, _ ->
            }

        if (willUseTitle) {
            builder.setTitle(getString(titleId!!))
        }
        return builder.create()
    }

}
