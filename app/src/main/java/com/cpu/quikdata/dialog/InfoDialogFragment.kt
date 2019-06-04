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

/**
 * A simple [Fragment] subclass.
 *
 */
class InfoDialogFragment : DialogFragment() {

    companion object {
        const val TAG = "INFO_DIALOG_FRAGMENT"
        private const val TITLE_ID_KEY = "TITLE_ID_KEY"
        private const val TEXT_ID_KEY = "TEXT_ID_KEY"

        fun newInstance(titleId: Int, textId: Int) : InfoDialogFragment {
            val fragment = InfoDialogFragment()
            val bundle = Bundle()
            bundle.putInt(TITLE_ID_KEY, titleId)
            bundle.putInt(TEXT_ID_KEY, textId)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val titleId = arguments?.getInt(TITLE_ID_KEY)
        val title = getString(titleId!!)

        val textId = arguments?.getInt(TEXT_ID_KEY)
        val text = getString(textId!!)

        val builder = AlertDialog.Builder(context!!)
        builder.setTitle(title)
            .setMessage(text)
            .setPositiveButton("OK") { _, _ ->
            }
        return builder.create()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info_dialog, container, false)
    }


}
