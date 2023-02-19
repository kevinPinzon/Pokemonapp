package com.example.pokemonapp.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.domain.usecase.FirebaseSignUp
import com.example.pokemonapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpviewModel @Inject constructor(
    private val signUpUseCase: FirebaseSignUp
): ViewModel() {

    private val _signUpState: MutableLiveData<Resource<Boolean>> = MutableLiveData()
    val signUpState: LiveData<Resource<Boolean>>
    get() = _signUpState

    fun signUp(name: String, email: String, password: String) {
        viewModelScope.launch {
            signUpUseCase(name, email, password).onEach { state ->
                _signUpState.value = state
            }.launchIn(viewModelScope)
        }
    }
}