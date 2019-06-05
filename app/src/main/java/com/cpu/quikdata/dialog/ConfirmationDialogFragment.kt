package com.cpu.quikdata.dialog


import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment

import com.cpu.quikdata.R
import java.lang.reflect.Array.getInt

/**
 * A simple [Fragment] subclass.
 *
 */
class ConfirmationDialogFragment : DialogFragment() {

    companion object {
        const val TAG = "CONFIRMATION_DIALOG_FRAGMENT"
        private const val TITLE_ID_KEY = "TITLE_ID_KEY"
        private const val CONTENT_ID_KEY = "CONTENT_ID_KEY"

        fun newInstance(titleId: Int, textId: Int) : ConfirmationDialogFragment {
            val fragment = ConfirmationDialogFragment()
            val bundle = Bundle()
            bundle.putInt(TITLE_ID_KEY, titleId)
            bundle.putInt(CONTENT_ID_KEY, textId)
            fragment.arguments = bundle
            return fragment
        }
    }

    private var mOnPositiveButtonListener: (() -> Unit)? = null
    private var mOnNegativeButtonListener: (() -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val titleId = arguments?.getInt(TITLE_ID_KEY)
        val title = getString(titleId!!)

        val contentId = arguments?.getInt(CONTENT_ID_KEY)
        val content = LayoutInflater.from(context!!).inflate(contentId!!, null)

        val builder = AlertDialog.Builder(context!!)
        builder.setTitle(title)
            .setView(content)
            .setNegativeButton(R.string.text_cancel) { _, _ ->
                dismiss()
                onNegativeButtonListener?.invoke()
            }
            .setPositiveButton(getString(R.string.text_ok)) { _, _ ->
                dismiss()
                onPositiveButtonListener?.invoke()
            }
        return builder.create()
    }

    var onPositiveButtonListener: (() -> Unit)? = null
        set(value) {
            field = value
            mOnPositiveButtonListener = field
        }

    var onNegativeButtonListener: (() -> Unit)? = null
        set(value) {
            field = value
            mOnNegativeButtonListener = field
        }
}