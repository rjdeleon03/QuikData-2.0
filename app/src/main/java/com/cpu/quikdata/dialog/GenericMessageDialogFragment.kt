package com.cpu.quikdata.dialog


import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment

import com.cpu.quikdata.R
import kotlinx.android.synthetic.main.dialog_generic_message.view.*

/**
 * A simple [Fragment] subclass.
 *
 */
class GenericMessageDialogFragment : DialogFragment() {

    companion object {
        const val TAG = "GENERIC_MESSAGE_DIALOG_FRAGMENT"
        private const val TITLE_ID_KEY = "TITLE_ID_KEY"
        private const val CONTENT_ID_KEY = "CONTENT_ID_KEY"

        @JvmStatic
        fun start(titleId: Int=-1, textId: Int) : GenericMessageDialogFragment {
            val fragment = GenericMessageDialogFragment()
            val bundle = Bundle()
            bundle.putInt(TITLE_ID_KEY, titleId)
            bundle.putInt(CONTENT_ID_KEY, textId)
            fragment.arguments = bundle
            return fragment
        }
    }

    private var mOnDialogOkListener: (() -> Unit)? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val title = arguments?.getInt(TITLE_ID_KEY)?.let {
            if (it != -1) getString(it) else null
        }

        val content = LayoutInflater.from(context!!).inflate(R.layout.dialog_generic_message, null)
        arguments?.getInt(CONTENT_ID_KEY)?.let {
            content.genericMessageDialogText.setText(it)
        }

        val builder = AlertDialog.Builder(context!!).apply {
            title?.let { setTitle(title) }
            setView(content)
            setPositiveButton(getString(R.string.text_ok)) { _, _ ->
                mOnDialogOkListener?.invoke()
            }
        }
        return builder.create()
    }

    fun setOnOkClickListener(listener: () -> Unit) {
        mOnDialogOkListener = listener
    }

}
