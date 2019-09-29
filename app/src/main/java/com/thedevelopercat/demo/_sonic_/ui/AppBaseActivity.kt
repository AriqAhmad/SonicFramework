package com.thedevelopercat.demo._sonic_.ui

import androidx.databinding.ViewDataBinding
import com.thedevelopercat.sonic.ui.activities.SonicActivity
import com.thedevelopercat.sonic.viewModels.SonicViewModel

abstract class AppBaseActivity<Binding: ViewDataBinding, ViewModel: SonicViewModel> :
    SonicActivity<Binding, ViewModel>() {

}
