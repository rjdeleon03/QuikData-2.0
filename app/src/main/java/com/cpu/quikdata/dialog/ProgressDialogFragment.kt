package com.cpu.quikdata.dialog


import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment

import com.cpu.quikdata.R
import com.cpu.quikdata.common.ProgressNotification
import kotlinx.android.synthetic.main.dialog_progress.*

/**
 * A simple [Fragment] subclass.
 *
 */
class ProgressDialogFragment : DialogFragment() {

    companion object {
        const val TAG = "PROGRESS_DIALOG_FRAGMENT"
        const val TEXT_TAG = "PROGRESS_DIALOG_TEXT_TAG"
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

    private var mOnDialogCanceledListener: (() -> Unit)? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val titleId = arguments?.getInt(TITLE_ID_KEY, -1)
        val willUseTitle = titleId != -1

        val contentId = arguments?.getInt(CONTENT_ID_KEY)
        val content = LayoutInflater.from(context!!).inflate(contentId!!, null)

        val builder = AlertDialog.Builder(context!!)
            .setView(content)
            .setPositiveButton(R.string.text_cancel) { dialog, _ ->
                dialog.cancel()
            }
        if (willUseTitle) {
            builder.setTitle(getString(titleId!!))
        }
        val dialog = builder.create()
        dialog.setCanceledOnTouchOutside(false)
        return dialog
    }

    override fun onSaveInstanceState(outState: Bundle) {
        dialog?.progressDialogText?.text?.let {
            outState.putString(TEXT_TAG, it.toString())
        }
        super.onSaveInstanceState(outState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val dialogText = savedInstanceState?.getString(TEXT_TAG)
        dialog?.progressDialogText?.let {
            it.text = dialogText
        }
    }

    fun setOnDialogCanceledListener(listener: () -> Unit) {
        mOnDialogCanceledListener = listener
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        mOnDialogCanceledListener?.invoke()
    }

    fun updateBasedOnProgress(progress: ProgressNotification) {
        dialog?.let {
            if (progress == ProgressNotification.FORM_SUBMITTED) {
                it.progressDialogText.setText(R.string.form_item_submitting_image)
            }
        }
    }
}
