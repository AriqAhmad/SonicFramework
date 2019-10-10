package com.thedevelopercat.demo.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import com.thedevelopercat.demo.R
import com.thedevelopercat.demo.databinding.FragmentMainBinding
import com.thedevelopercat.demo.models.user.response.UserDetailsResponse
import com.thedevelopercat.demo.viewHolders.MainViewModel
import com.thedevelopercat.sonic.ui.fragments.SonicFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainFragment: SonicFragment<FragmentMainBinding, MainViewModel>() {

    companion object{
        fun newInstance(bundle: Bundle? = null): MainFragment{
            val fragment = MainFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getLayout(): Int {
        return R.layout.fragment_main
    }

    override fun getViewModelClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun initViews() {
        binding?.viewModel = viewModel
        viewModel?.getUserDetails()?.observe(this, Observer {
            val response = (it as? UserDetailsResponse)
            text.text = response?.data?.name
        })
    }

}