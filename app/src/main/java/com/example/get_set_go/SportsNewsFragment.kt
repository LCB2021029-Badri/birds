package com.example.get_set_go

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import coil.load
import com.example.get_set_go.databinding.FragmentSportsNewsBinding
import com.example.get_set_go.model.SportsViewModel

class NewsDetailsFragment : Fragment() {

    private val sportsViewModel: SportsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentSportsNewsBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSportsNewsBinding.bind(view)

        // Attach an observer on the currentSport to update the UI automatically when the data
        // changes.
        sportsViewModel.currentSport.observe(this.viewLifecycleOwner) {
            binding.titleDetail.text = getString(it.titleResourceId)
            binding.sportsImageDetail.load(it.sportsImageBanner)
            binding.newsDetail.text = getString(it.newsDetails)
        }
    }
}