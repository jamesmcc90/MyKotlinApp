package com.jamesapprockfield.myapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.jamesapprockfield.myapp.R

class HomeFragment : Fragment() {

    private lateinit var tabsViewModel: TabsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tabsViewModel =
            ViewModelProviders.of(this).get(TabsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        tabsViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}