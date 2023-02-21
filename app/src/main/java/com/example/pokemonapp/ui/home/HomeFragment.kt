package com.example.pokemonapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.os.bundleOf
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemonapp.R
import com.example.pokemonapp.data.model.PokeRegion
import com.example.pokemonapp.databinding.FragmentHomeBinding
import com.example.pokemonapp.ui.components.adapter.RegionAdapter

class HomeFragment : Fragment(), ClickListener{

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()
    private var mAdapter: RegionAdapter?= null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAdapter = RegionAdapter(this)
        binding.recyclerview.layoutManager = LinearLayoutManager(context)
        binding.recyclerview.adapter = mAdapter

        requireActivity().onBackPressedDispatcher.addCallback(this) {}
        initObservers()
        viewModel.getRegions()
    }

    private fun initObservers() {
        viewModel.progressState.observe(viewLifecycleOwner) { show ->
            binding.progress.isVisible = show
        }

        viewModel.regionsLiveList.observe(viewLifecycleOwner) { state ->
            println("state: $state")
            mAdapter?.setItems(list = state)
            binding.progress.isInvisible = true
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun regionSelect(data: PokeRegion) {
        val bundle = bundleOf("regionName" to data.name)
        findNavController().navigate(R.id.action_navigation_regions_to_navigation_pokemons,
            bundle)
    }

}

interface ClickListener {
    fun regionSelect(data: PokeRegion)
}