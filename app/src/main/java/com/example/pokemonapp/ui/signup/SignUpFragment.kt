package com.example.pokemonapp.ui.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.pokemonapp.R
import com.example.pokemonapp.databinding.SignupFragmentBinding
import com.example.pokemonapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment: Fragment() {

    private var _binding: SignupFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SignUpviewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = SignupFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        initListeners()

    }

    private fun initObservers() {
        viewModel.signUpState.observe(viewLifecycleOwner) { state ->
            when(state) {
                is Resource.Success -> {
                    handleLoading(isLoading = false)
                    activity?.onBackPressed()
                    Toast.makeText(
                        requireContext(),
                        "Se ha creado una sesion correctamente.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is Resource.Error -> {
                    handleLoading(isLoading = false)
                    Toast.makeText(
                        requireContext(),
                        state.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is Resource.Loading -> handleLoading(isLoading = true)
                else -> Unit
            }
        }
    }

    private fun handleLoading(isLoading: Boolean) {
        with(binding) {
            if (isLoading) {
                bSignUp.text = ""
                bSignUp.isEnabled = false
                pbSignUp.visibility = View.VISIBLE
            } else {
                pbSignUp.visibility = View.GONE
                bSignUp.text = getString(R.string.signup_button)
                bSignUp.isEnabled = true
            }
        }
    }

    private fun initListeners() {
        with(binding) {
            bSignUp.setOnClickListener {
                viewModel.signUp(
                    binding.etEmail.text.toString(), binding.etPassword.text.toString()
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}