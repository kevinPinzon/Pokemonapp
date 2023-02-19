package com.example.pokemonapp.domain.usecase

import com.example.pokemonapp.domain.model.User
import com.example.pokemonapp.domain.repository.AuthRepository
import com.example.pokemonapp.domain.repository.UserRepository
import com.example.pokemonapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FirebaseSignUp @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(name: String, email: String, password: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading)
        val userUID: String = authRepository.signup(email, password)
        if (userUID.isNotEmpty()) {
            userRepository.createUser(User(userUID, name, email))
            emit(Resource.Success(true))
        } else {
            emit(Resource.Error("SignUp error"))
        }
    }
}