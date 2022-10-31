package com.example.get_set_go

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.get_set_go.adapter.HometopAdapter
import com.example.get_set_go.adapter.StyleDiamondAdapter
import com.example.get_set_go.databinding.FragmentHomeListBinding
import com.example.get_set_go.databinding.FragmentStyleDiamondListBinding
import com.example.get_set_go.model.HomeTopModel

class HomeListFragment : Fragment() {


    private val homeTopModel: HomeTopModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentHomeListBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentStyleDiamondListBinding.bind(view)

        // Initialize the adapter and set it to the RecyclerView.
        val adapter = HometopAdapter {
            // Update the user selected sport as the current sport in the shared viewmodel
            // This will automatically update the dual pane content
            homeTopModel.updateCurrentSport(it)
            // Navigate to the details screen
//            val action = SportsListFragmentDirections.actionSportsListFragmentToNewsFragment()
//            this.findNavController().navigate(action)
            startActivity(Intent(this.requireContext(),CameraActivity::class.java))
        }
        binding.recyclerView.adapter = adapter
        adapter.submitList(homeTopModel.sportsData)
    }

}