package com.e.accessiblenews.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider

import com.e.accessiblenews.R
import com.e.accessiblenews.databinding.FragmentOverviewBinding

class OverviewFragment : Fragment() {
    private lateinit var binding: FragmentOverviewBinding
    private lateinit var overviewViewModel: OverviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_overview,
            container,
            false
        )

        overviewViewModel = ViewModelProvider(this).get(OverviewViewModel::class.java)
        binding.lifecycleOwner = this
        binding.overviewViewModel = overviewViewModel
        return binding.root
    }

}
