package com.thedevelopercat.demo.ui

import android.content.Intent
import android.os.Bundle
import com.thedevelopercat.demo.R
import com.thedevelopercat.demo._sonic_.ui.AppBaseActivity
import com.thedevelopercat.demo._sonic_.ui.AppBaseFragment
import com.thedevelopercat.sonic.ui.activities.FragmentContainerActivity
import com.thedevelopercat.sonic.ui.activities.setFragmentType
import com.thedevelopercat.sonic.ui.fragments.SonicFragment

class AppFragmentContainerActivity : FragmentContainerActivity() {

    override fun getFragmentInstance(type: Int?): SonicFragment<*, *> {
        return MainFragment.newInstance()
    }

    override fun getToolbarTitleColor(): Int {
        return R.color.black
    }

    override fun getToolbarTitle(type: Int?): Int {
        return when (type) {
            else -> R.string.app_name
        }
    }

    override fun shouldAddToBackStack(): Boolean {
        return when (type) {
            else -> false
        }
    }

    override fun initViews() {
        super.initViews()
        setToolbarAsTransparent(R.color.white)
        matchLayoutToParents()
    }
}

fun AppBaseFragment<*, *>.startFragmentActivity(
    type: Int, bundle: Bundle = Bundle(),
    clearTop: Boolean = false
) {
    val intent = Intent(activity, AppFragmentContainerActivity::class.java)
    intent.putExtras(bundle)
    intent.setFragmentType(type)
    if (clearTop) {
        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    startActivity(intent)
}

fun AppBaseActivity<*, *>.startFragmentActivity(
    type: Int, bundle: Bundle = Bundle(),
    clearTop: Boolean = false
) {
    val intent = Intent(this, AppFragmentContainerActivity::class.java)
    intent.putExtras(bundle)
    intent.setFragmentType(type)
    if (clearTop) {
        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    startActivity(intent)
}