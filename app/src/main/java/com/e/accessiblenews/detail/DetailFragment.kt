package com.e.accessiblenews.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider

import com.e.accessiblenews.R
import com.e.accessiblenews.databinding.DetailFragmentBinding

class DetailFragment : Fragment() {

    private lateinit var detailViewModelFactory: DetailViewModelFactory
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var binding: DetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.detail_fragment,
            container,
            false
        )

        val application = requireNotNull(activity).application
        val selectedArticle = DetailFragmentArgs.fromBundle(requireArguments()).selectedArticle
        detailViewModelFactory = DetailViewModelFactory(selectedArticle, application)
        detailViewModel = ViewModelProvider(this, detailViewModelFactory).get(DetailViewModel::class.java)
        binding.detailViewModel = detailViewModel
        binding.lifecycleOwner = this

        // Load article URL when Read More button is pressed
        val openURL = Intent(Intent.ACTION_VIEW)
        openURL.data = Uri.parse(selectedArticle.url)
        binding.readMoreButton.setOnClickListener {
            startActivity(openURL)
        }

        return binding.root
    }
}
