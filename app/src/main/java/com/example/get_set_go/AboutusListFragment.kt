package com.example.get_set_go

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.get_set_go.adapter.AboutusAdapter
import com.example.get_set_go.adapter.SportsAdapter
import com.example.get_set_go.databinding.FragmentAboutusListBinding
import com.example.get_set_go.databinding.FragmentSportsListBinding
import com.example.get_set_go.model.AboutusViewModel
import com.example.get_set_go.model.SportsViewModel


class AboutusListFragment : Fragment() {
    private val aboutusViewModel: AboutusViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentAboutusListBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentAboutusListBinding.bind(view)


        // Initialize the adapter and set it to the RecyclerView.
        val adapter = AboutusAdapter {
            // Update the user selected sport as the current sport in the shared viewmodel
            // This will automatically update the dual pane content
            aboutusViewModel.updateCurrentSport(it)
            // Navigate to the details screen
//            val action = SportsListFragmentDirections.actionSportsListFragmentToNewsFragment()
//            this.findNavController().navigate(action)
            findNavController().navigate(R.id.action_aboutusListFragment_to_aboutusNewsFragment2)
        }
        binding.recyclerView.adapter = adapter
        adapter.submitList(aboutusViewModel.sportsData)
    }
}