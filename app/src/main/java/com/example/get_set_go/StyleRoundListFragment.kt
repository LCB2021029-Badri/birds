package com.example.get_set_go

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.get_set_go.adapter.StyleOvalAdapter
import com.example.get_set_go.adapter.StyleRoundAdapter
import com.example.get_set_go.databinding.FragmentStyleOvalListBinding
import com.example.get_set_go.databinding.FragmentStyleRoundListBinding
import com.example.get_set_go.model.StyleOvalViewModel
import com.example.get_set_go.model.StyleRoundViewModel

class StyleRoundListFragment : Fragment() {

    private val styleRoundViewModel: StyleRoundViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentStyleRoundListBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentStyleRoundListBinding.bind(view)

        // Initialize the adapter and set it to the RecyclerView.
        val adapter = StyleRoundAdapter {
            // Update the user selected sport as the current sport in the shared viewmodel
            // This will automatically update the dual pane content
            styleRoundViewModel.updateCurrentStyle(it)
            // Navigate to the details screen
//            val action = SportsListFragmentDirections.actionSportsListFragmentToNewsFragment()
//            this.findNavController().navigate(action)
            findNavController().navigate(R.id.action_styleRoundListFragment_to_styleRoundNewsFragment)
        }
        binding.recyclerView.adapter = adapter
        adapter.submitList(styleRoundViewModel.styleRoundData)
    }
}