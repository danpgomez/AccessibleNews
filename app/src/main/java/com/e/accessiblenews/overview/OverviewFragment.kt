package com.e.accessiblenews.overview

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.e.accessiblenews.R
import com.e.accessiblenews.databinding.FragmentOverviewBinding

class OverviewFragment : Fragment() {
    private lateinit var binding: FragmentOverviewBinding
    private lateinit var overviewViewModel: OverviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_overview,
            container,
            false
        )

        overviewViewModel = ViewModelProvider(this).get(OverviewViewModel::class.java)
        binding.lifecycleOwner = this
        binding.overviewViewModel = overviewViewModel
        binding.newsList.adapter = NewsListAdapter(NewsListAdapter.OnClickListener { selectedArticle ->
            overviewViewModel.showDetailsOf(selectedArticle)
        })

        overviewViewModel.navigateToSelectedArticle.observe(viewLifecycleOwner, Observer { selectedArticle ->
            if (selectedArticle != null) {
                this.findNavController().navigate(OverviewFragmentDirections.actionOverviewFragmentToDetailFragment(selectedArticle))
                overviewViewModel.doneNavigatingToDetails()
            }
        })

        return binding.root
    }
}
