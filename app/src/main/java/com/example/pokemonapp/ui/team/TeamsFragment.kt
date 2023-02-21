package com.example.pokemonapp.ui.team

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.snapshots.Snapshot.Companion.observe
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.pokemonapp.databinding.FragmentTeamsBinding
import com.example.pokemonapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamsFragment : Fragment() {

    private var _binding: FragmentTeamsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TeamsViewModel by viewModels()
    lateinit var pref: SharedPreferences
    private lateinit var userID: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTeamsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = requireActivity().getSharedPreferences("MyPref", 0)
        userID = pref.getString("userId", "").toString()
        initObservers()
        initListeners()
        viewModel.getTeams(userID)
    }

    private fun initObservers() {
        viewModel.teamsState.observe(viewLifecycleOwner) { state ->
            when(state) {
                is Resource.Success -> {
                    state
                    println(state)
                }
                is Resource.Error -> {
                    state
                    println(state)
                }
                is Resource.Loading ->{
                    state
                    println(state)
                }
                else -> {
                    state
                    println(state)
                }
            }
        }
    }

    private fun initListeners() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}