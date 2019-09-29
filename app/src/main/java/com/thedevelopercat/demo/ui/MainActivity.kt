package com.thedevelopercat.demo.ui

import androidx.lifecycle.Observer
import com.thedevelopercat.demo.R
import com.thedevelopercat.demo._sonic_.ui.AppBaseActivity
import com.thedevelopercat.demo.databinding.ActivityMainBinding
import com.thedevelopercat.demo.viewHolders.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppBaseActivity<ActivityMainBinding, MainViewModel>() {

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun getViewModelClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun initViews() {
        binding?.viewModel = viewModel
        viewModel?.getUserDetails()?.observe(this, Observer {
            text.text = it.data?.name
        })
    }
}