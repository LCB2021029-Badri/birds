package com.example.get_set_go

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.get_set_go.adapter.AboutusAdapter
import com.example.get_set_go.adapter.HometopAdapter
import com.example.get_set_go.databinding.FragmentAboutusListBinding
import com.example.get_set_go.databinding.FragmentHomepageBinding
import com.example.get_set_go.model.AboutusViewModel
import com.example.get_set_go.model.HomeTopModel

class HomepageFragment : Fragment() {

    lateinit var binding: FragmentHomepageBinding
    private val hometopModel: HomeTopModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentHomepageBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomepageBinding.bind(view)

        // Initialize the adapter and set it to the RecyclerView.
        val adapter = HometopAdapter {
            // Update the user selected sport as the current sport in the shared viewmodel
            // This will automatically update the dual pane content
            hometopModel
                .updateCurrentSport(it)
            // Navigate to the details screen
//            val action = SportsListFragmentDirections.actionSportsListFragmentToNewsFragment()
//            this.findNavController().navigate(action)
//            findNavController().navigate(R.id.action_homepageFragment2_to_hometopNewsFragment)
            val intent = Intent(this@HomepageFragment.requireContext(),ThirdActivity::class.java)
            startActivity(intent)
        }
        binding.recyclerView.adapter = adapter
        adapter.submitList(hometopModel.sportsData)


        binding.getStarted.setOnClickListener {
            startActivity(Intent(this@HomepageFragment.requireContext(),CameraActivity::class.java))
        }
    }
}