package com.example.pokemonapp.domain.usecase

import com.example.pokemonapp.domain.repository.AuthRepository
import com.example.pokemonapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FirebaseSignUp @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(email: String, password: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading)
        val isSignUpSuccessfully = authRepository.signup(email, password)
        if (isSignUpSuccessfully) {
            emit(Resource.Success(true))
        } else {
            emit(Resource.Error("SignUp error"))
        }
    }
}