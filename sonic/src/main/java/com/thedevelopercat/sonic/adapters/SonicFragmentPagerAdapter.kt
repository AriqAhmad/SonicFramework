package com.thedevelopercat.sonic.adapters

import androidx.fragment.app.FragmentPagerAdapter
import com.thedevelopercat.sonic.ui.fragments.SonicFragment

class SonicFragmentPagerAdapter(
    fm: androidx.fragment.app.FragmentManager,
    private val pages: List<SonicFragment<*,*>>
) : FragmentPagerAdapter(fm) {

    override fun getItem(i: Int): SonicFragment<*,*> {
        return pages[i]
    }

    override fun getCount(): Int {
        return pages.size
    }
}
