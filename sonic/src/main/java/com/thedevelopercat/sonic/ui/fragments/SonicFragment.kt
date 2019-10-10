package com.thedevelopercat.sonic.ui.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.thedevelopercat.sonic.ui.activities.SonicActivity
import com.thedevelopercat.sonic.helper.SonicLogger
import com.thedevelopercat.sonic.viewModels.SonicViewModel

abstract class SonicFragment<Binding: ViewDataBinding, ViewModel: SonicViewModel?> : Fragment(), View.OnClickListener {

    protected lateinit var sonicActivity: SonicActivity<*,*>
    protected lateinit var root: View
    protected var viewModel: ViewModel? = null
    protected var binding: Binding? = null

    abstract fun getLayout(): Int
    protected abstract fun getViewModelClass(): Class<ViewModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sonicActivity = activity as SonicActivity<*,*>
        binding = DataBindingUtil.inflate(inflater, getLayout(),container,false)
        binding?.lifecycleOwner = this
        viewModel = ViewModelProviders.of(this).get(getViewModelClass())
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    abstract fun initViews()

    override fun onClick(v: View) {}


    // Event Listeners
    fun setOnClickListener(vararg views: View) {
        for (view in views) {
            view.setOnClickListener(this)
        }
    }

    fun removeOnClickListener(vararg views: View) {
        for (view in views) {
            view.setOnClickListener(null)
        }
    }








    // Next Activity
    fun startNextActivity(activityClass: Class<out Activity>, bundle: Bundle? = null) {
        val intent = Intent(sonicActivity, activityClass)
        bundle?.let { intent.putExtras(it) }
        startActivity(intent)
    }

    fun startNextActivityAndFinishCurrent(
        activityClass: Class<out Activity>,
        bundle: Bundle? = null
    ) {
        val intent = Intent(sonicActivity, activityClass)
        bundle?.let { intent.putExtras(it) }
        startActivity(intent)
        sonicActivity.finish()
    }

    fun startNextActivityAndClearTop(
        activityClass: Class<out Activity>,
        bundle: Bundle?
    ) {
        val intent = Intent(sonicActivity, activityClass)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        bundle?.let { intent.putExtras(it) }
        sonicActivity.finish()
    }






    //Toolbar
    fun setToolBarIconColor(@ColorRes color: Int) {
        sonicActivity.setToolBarIconColor(color)
    }

    fun initToolbar(title: String?) {
        sonicActivity.initToolbar(title)
    }

    fun setToolBarElevation(elevation: Float) {
        sonicActivity.setToolBarElevation(elevation)
    }

    fun setToolbarAsTransparent(@ColorRes color: Int) {
        sonicActivity.setToolbarAsTransparent(color)
    }

    fun setToolbarAsOpaque(@ColorRes color: Int) {
        sonicActivity.setToolbarAsOpaque(color)
    }

    fun initToolBar(title: Int?) {
        if (title == null) {
            SonicLogger.debug(this, "Toolbar title is null")
            return
        }
        val toolbarTitle = resources.getString(title)
        initToolbar(toolbarTitle)
    }






    // Transactions
    fun addFragmentReplace(
        containerID: Int,
        fragment: Fragment,
        addToBackStack: Boolean
    ) {
        val fragmentTransaction = fragmentManager?.beginTransaction()
        if (addToBackStack) {
            fragmentTransaction?.addToBackStack(null)
        }
        fragmentTransaction?.replace(containerID, fragment)?.commit()
    }

    fun getTopFragment(): Fragment? {
        fragmentManager?.run {
            return when (backStackEntryCount) {
                0 -> null
                else -> findFragmentByTag(getBackStackEntryAt(backStackEntryCount - 1).name)
            }
        }
        return null
    }
}
