package com.thedevelopercat.sonic.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.thedevelopercat.sonic.R
import kotlinx.android.synthetic.main.dialog_fragment_custom_progress.*

class SonicProgressDialogFragment : DialogFragment() {

    companion object {
        private const val TITLE = "title"

        fun newInstance(title: String = ""): SonicProgressDialogFragment {
            val fragment = SonicProgressDialogFragment()
            val args = Bundle()
            args.putString(TITLE, title)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_fragment_custom_progress, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title = arguments?.getString(TITLE, "") ?: ""
        this.title.text = title
    }
}