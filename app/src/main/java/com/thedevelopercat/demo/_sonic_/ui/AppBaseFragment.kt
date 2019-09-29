package com.thedevelopercat.demo._sonic_.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.thedevelopercat.sonic.ui.fragments.SonicFragment
import com.thedevelopercat.sonic.viewModels.SonicViewModel


abstract class AppBaseFragment<Binding: ViewDataBinding, ViewModel: SonicViewModel> :
    SonicFragment<Binding, ViewModel>() {

    protected lateinit var appBaseActivity: AppBaseActivity<*, *>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        appBaseActivity = activity as AppBaseActivity<*, *>
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    abstract override fun initViews()
}
