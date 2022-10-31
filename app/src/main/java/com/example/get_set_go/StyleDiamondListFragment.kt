package com.example.get_set_go

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.get_set_go.adapter.StyleDiamondAdapter
import com.example.get_set_go.adapter.StyleOvalAdapter
import com.example.get_set_go.adapter.StyleRoundAdapter
import com.example.get_set_go.databinding.FragmentStyleDiamondListBinding
import com.example.get_set_go.databinding.FragmentStyleOvalListBinding
import com.example.get_set_go.databinding.FragmentStyleRoundListBinding
import com.example.get_set_go.model.StyleDiamondViewModel
import com.example.get_set_go.model.StyleOvalViewModel
import com.example.get_set_go.model.StyleRoundViewModel

class StyleDiamondListFragment : Fragment() {

    private val styleDiamondViewModel: StyleDiamondViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentStyleDiamondListBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentStyleDiamondListBinding.bind(view)

        // Initialize the adapter and set it to the RecyclerView.
        val adapter = StyleDiamondAdapter {
            // Update the user selected sport as the current sport in the shared viewmodel
            // This will automatically update the dual pane content
            styleDiamondViewModel.updateCurrentStyle(it)
            // Navigate to the details screen
//            val action = SportsListFragmentDirections.actionSportsListFragmentToNewsFragment()
//            this.findNavController().navigate(action)
            findNavController().navigate(R.id.action_styleDiamondListFragment_to_styleDiamondNewsFragment)
        }
        binding.recyclerView.adapter = adapter
        adapter.submitList(styleDiamondViewModel.styleDiamondData)
    }
}