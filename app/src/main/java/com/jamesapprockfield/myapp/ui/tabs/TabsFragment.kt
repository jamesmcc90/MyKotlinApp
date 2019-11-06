package com.jamesapprockfield.myapp.ui.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.jamesapprockfield.myapp.R
import com.jamesapprockfield.myapp.ui.home.TabsViewModel

class TabsFragment : Fragment() {

    private lateinit var tabsViewModel: TabsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tabsViewModel =
            ViewModelProviders.of(this).get(TabsViewModel::class.java)
        val root = inflater.inflate(R.layout.tabs, container, false)

        return root
    }
}