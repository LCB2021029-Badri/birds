package com.example.get_set_go

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.get_set_go.adapter.SportsAdapter
import com.example.get_set_go.adapter.StyleOvalAdapter
import com.example.get_set_go.databinding.FragmentSportsListBinding
import com.example.get_set_go.databinding.FragmentStyleOvalListBinding
import com.example.get_set_go.model.SportsViewModel
import com.example.get_set_go.model.StyleOvalViewModel

class StyleOvalListFragment : Fragment() {

    private val styleOvalViewModel: StyleOvalViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentStyleOvalListBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentStyleOvalListBinding.bind(view)

        // Initialize the adapter and set it to the RecyclerView.
        val adapter = StyleOvalAdapter {
            // Update the user selected sport as the current sport in the shared viewmodel
            // This will automatically update the dual pane content
            styleOvalViewModel.updateCurrentStyle(it)
            // Navigate to the details screen
//            val action = SportsListFragmentDirections.actionSportsListFragmentToNewsFragment()
//            this.findNavController().navigate(action)
            findNavController().navigate(R.id.action_styleOvalListFragment_to_styleOvalNewsFragment)
        }
        binding.recyclerView.adapter = adapter
        adapter.submitList(styleOvalViewModel.styleOvalData)
    }
}
