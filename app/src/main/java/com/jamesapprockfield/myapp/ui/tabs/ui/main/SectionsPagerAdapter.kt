package com.jamesapprockfield.myapp.ui.tabs.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.jamesapprockfield.myapp.R
import com.jamesapprockfield.myapp.ui.tabs.Tab1Fragment
import com.jamesapprockfield.myapp.ui.tabs.Tab2Fragment
import com.jamesapprockfield.myapp.ui.tabs.Tab3Fragment

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                Tab1Fragment()
            }
            1 -> Tab2Fragment()
            else -> {
                return Tab3Fragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 2
    }
}