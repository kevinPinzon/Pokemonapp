package com.example.pokemonapp.ui.sigin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.data.retrofitHelpers.APIservice
import com.example.pokemonapp.data.retrofitHelpers.RetrofitHelper
import com.example.pokemonapp.domain.model.User
import com.example.pokemonapp.domain.usecase.FirebaseLogin
import com.example.pokemonapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val loginUseCase: FirebaseLogin
): ViewModel() {

    private val _loginState: MutableLiveData<Resource<User>> = MutableLiveData()
    private val _pokeState: MutableLiveData<Resource<User>> = MutableLiveData()
    val loginState: LiveData<Resource<User>>
        get() = _loginState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            loginUseCase(email, password).onEach { state ->
                _loginState.value = state
            }.launchIn(viewModelScope)
        }
    }

    fun loginWithGoogle() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitHelper().getPokeApi().create(APIservice::class.java)
                .getRegions("region")
            val resultRegions = call.body()
            println("resultRegions: $resultRegions")
        }
    }


}