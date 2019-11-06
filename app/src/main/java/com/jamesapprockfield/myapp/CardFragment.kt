package com.jamesapprockfield.myapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders

class CardFragment : Fragment() {

    private lateinit var cardViewModel: CardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        cardViewModel =
            ViewModelProviders.of(this).get(CardViewModel::class.java)
        val root = inflater.inflate(R.layout.activity_card, container, false)

        return root
    }
}
