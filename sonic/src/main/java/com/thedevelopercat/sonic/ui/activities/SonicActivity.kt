package com.thedevelopercat.sonic.ui.activities

import android.animation.AnimatorInflater
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.MenuItem
import android.view.View
import android.view.View.OnClickListener
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.thedevelopercat.sonic.R
import com.thedevelopercat.sonic.helper.SonicLogger
import com.thedevelopercat.sonic.viewModels.SonicViewModel
import com.google.android.material.appbar.AppBarLayout

// https://stackoverflow.com/questions/39483094/data-binding-class-not-generated
abstract class SonicActivity<Binding: ViewDataBinding, ViewModel : SonicViewModel> : AppCompatActivity(), OnClickListener {

    protected var viewModel: ViewModel? = null
    protected var binding: Binding? = null

    private var toolbar: Toolbar? = null
    private var appBarLayout: AppBarLayout? = null

    @ColorRes
    private var toolBarColor = R.color.colorPrimary

    protected abstract fun getLayout(): Int
    protected abstract fun getViewModelClass(): Class<ViewModel>
    protected abstract fun initViews()

    @ColorRes
    open fun getToolbarColor(): Int {
        return toolBarColor
    }

    open fun setToolbarColor(@ColorRes color: Int) {
        toolBarColor = color
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayout())
        binding?.lifecycleOwner = this
        viewModel = ViewModelProviders.of(this).get(getViewModelClass())
        initToolBar(getToolbarTitle())
        initViews()
    }


    open fun onNavPressed(){
        hideKeyBoard()
        onBackPressed()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onNavPressed()
                return true
            }
        }
        return false
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_MENU) {
            // do nothing
            true
        } else super.onKeyDown(keyCode, event)
    }

    override fun onClick(v: View) {}

    protected fun hideKeyBoard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }


    // Event Listeners
    open fun setOnClickListener(vararg views: View) {
        for (view in views) {
            view.setOnClickListener(this)
        }
    }

    open fun removeOnClickListener(vararg views: View) {
        for (view in views) {
            view.setOnClickListener(null)
        }
    }


    // Fragment
    open fun addFragmentReplace(
        containerID: Int,
        fragment: Fragment,
        addToBackStack: Boolean
    ) {

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.javaClass.name)
        }
        fragmentTransaction.replace(containerID, fragment).commit()
    }

    open fun getTopFragment(): Fragment? {
        supportFragmentManager.run {
            return when (backStackEntryCount) {
                0 -> null
                else -> findFragmentByTag(getBackStackEntryAt(backStackEntryCount - 1).name)
            }
        }
    }


    // Next Activity
    open fun startNextActivity(
        activityClass: Class<out Activity>,
        bundle: Bundle? = null
    ) {
        val intent = Intent(this, activityClass)
        bundle?.let { intent.putExtras(it) }
        startActivity(intent)
    }

    open fun startNextActivityAndFinishCurrent(
        activityClass: Class<out Activity>,
        bundle: Bundle? = null
    ) {
        val intent = Intent(this, activityClass)
        bundle?.let { intent.putExtras(it) }
        startActivity(intent)
        finish()
    }

    open fun startNextActivityAndClearTop(
        activityClass: Class<out Activity>,
        bundle: Bundle?
    ) {
        val intent = Intent(this, activityClass)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        bundle?.let { intent.putExtras(it) }
        finish()
    }


    // Toolbar
    open fun getToolbarTitle(): Int? {
        return null
    }

    open fun getToolbarTitleColor(): Int {
        return toolBarColor
    }

    open fun setToolBarIconColor(@ColorRes color: Int) {
        val actionBar = supportActionBar
        actionBar?.setBackgroundDrawable(
            ColorDrawable(resources.getColor(color))
        )
    }

    open fun getToolbarIcon(): Int {
        return 0
    }

    open fun initToolbar(title: String?) {
        appBarLayout = findViewById(R.id.app_bar_layout)
        toolbar = findViewById(R.id.toolbar)

        if (toolbar == null) {
            SonicLogger.debug(this, "Toolbar is null")
            return
        }

        setSupportActionBar(toolbar)

        if (toolBarColor != -1) {
            supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(toolBarColor)))
        }
        supportActionBar?.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(getToolbarIcon())
        supportActionBar?.setHomeButtonEnabled(true)
        toolbar?.setTitleTextColor(
            ContextCompat.getColor(
                this, getToolbarTitleColor()
            )
        )
        toolbar?.setSubtitleTextColor(ContextCompat.getColor(this, R.color.black))
    }

    open fun setToolBarElevation(elevation: Float) {
        if (appBarLayout != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                appBarLayout?.elevation = elevation
                toolbar?.elevation = elevation
                appBarLayout?.stateListAnimator = AnimatorInflater.loadStateListAnimator(
                    applicationContext,
                    R.animator.appbar_state_unelevated_animator
                )
            }
        } else {
            SonicLogger.debug(this, "appBarLayout is null")
        }
    }

    open fun setToolbarAsTransparent(@ColorRes color: Int, hideIcon: Boolean = false) {
        setToolBarElevation(0.1f)
        toolBarColor = R.color.transparent
        setToolBarIconColor(color)
        supportActionBar?.setDisplayHomeAsUpEnabled(!hideIcon)
        supportActionBar?.setHomeButtonEnabled(!hideIcon)
    }

    open fun setToolbarAsOpaque(@ColorRes color: Int) {
        setToolBarElevation(4f)
        setToolBarIconColor(color)
        toolBarColor = color
    }


    open fun initToolBar(title: Int?) {
        if (title == null) {
            SonicLogger.debug(this, "Toolbar title is null")
            return
        }
        val toolbarTitle = resources.getString(title)
        initToolbar(toolbarTitle)
    }
}