package com.thedevelopercat.sonic.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.databinding.ViewDataBinding
import com.thedevelopercat.sonic.R
import com.thedevelopercat.sonic.ui.fragments.SonicFragment
import com.thedevelopercat.sonic.viewModels.SonicViewModel
import kotlinx.android.synthetic.main.activity_fragment_container.*

const val FRAGMENT_TYPE = "FRAGMENT_TYPE"

abstract class FragmentContainerActivity<Binding: ViewDataBinding, ViewModel : SonicViewModel> :
    SonicActivity<Binding, ViewModel>() {

    protected var type: Int? = null
    internal var isToolbarTransparent = false

    override fun getToolbarColor(): Int {
        return if(isToolbarTransparent) R.color.transparent else R.color.colorPrimary
    }

    override fun initViews() {
        setFragment()
        type = intent?.extras?.getInt(FRAGMENT_TYPE)
    }

    override fun getLayout(): Int {
        return R.layout.activity_fragment_container
    }

    open fun matchLayoutToParents() {
        container.layoutParams = ConstraintLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        container.invalidate()
        container.requestLayout()
    }

    open fun makeToolbarTransparent(@ColorRes iconTint: Int) {
        val set = ConstraintSet()
        set.clone(constraintLayout)
        set.connect(R.id.container, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)
        set.applyTo(constraintLayout)
        isToolbarTransparent = true
        setToolBarIconColor(iconTint)
        setToolbarAsTransparent(R.color.transparent)
    }

    private fun setFragment() {
        val fragment = getFragmentInstance(type)
        fragment.arguments = intent.extras
        addFragmentReplace(R.id.container, fragment, shouldAddToBackStack())
        initToolBar(getToolbarTitle(type))
    }

    abstract fun shouldAddToBackStack(): Boolean
    abstract fun getFragmentInstance(type: Int?): SonicFragment<Binding, ViewModel>
    @StringRes abstract fun getToolbarTitle(type: Int?): Int
}

fun Intent.setFragmentType(type: Int) {
    this.putExtra(FRAGMENT_TYPE, type)
}

fun SonicFragment<*,*>.startFragmentActivity(
    type: Int,
    bundle: Bundle = Bundle(),
    clearTop: Boolean = false
) {
    val intent = Intent(activity, FragmentContainerActivity::class.java)
    intent.putExtras(bundle)
    intent.setFragmentType(type)
    if (clearTop){
        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    startActivity(intent)
}

fun SonicActivity<*,*>.startFragmentActivity(
    type: Int,
    bundle: Bundle = Bundle(),
    clearTop: Boolean = false
) {
    val intent = Intent(this, FragmentContainerActivity::class.java)
    intent.putExtras(bundle)
    intent.setFragmentType(type)
    if (clearTop){
        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    startActivity(intent)
}