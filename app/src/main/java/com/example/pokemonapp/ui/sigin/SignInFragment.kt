package com.example.pokemonapp.ui.sigin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.pokemonapp.R
import com.example.pokemonapp.databinding.SiginFragmentBinding
import com.example.pokemonapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment() {

    private var _binding: SiginFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SignInViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SiginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        initListeners()

    }

    private fun initObservers() {
        viewModel.loginState.observe(viewLifecycleOwner) { state ->
            when(state) {
                is Resource.Success -> {
                    handleLoading(isLoading = false)
                    findNavController().navigate(R.id.action_nav_login_to_nav_home)
                    Toast.makeText(
                        requireContext(),
                        "Bienvenido",
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
                bLogin.text = ""
                bLogin.isEnabled = false
                pbLogin.visibility = View.VISIBLE
            } else {
                pbLogin.visibility = View.GONE
                bLogin.text = getString(R.string.login_button)
                bLogin.isEnabled = true
            }
        }
    }

    private fun initListeners() {
        with(binding) {
            bSignUp.setOnClickListener {
                findNavController().navigate(R.id.action_nav_login_to_nav_signup)
            }

            bLogin.setOnClickListener {
                viewModel.login(
                    binding.etEmail.text.toString(), binding.etPassword.text.toString()
                )
            }

            googleSignInButton.setOnClickListener {
                viewModel.login(
                    binding.etEmail.text.toString(), binding.etPassword.text.toString()
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}