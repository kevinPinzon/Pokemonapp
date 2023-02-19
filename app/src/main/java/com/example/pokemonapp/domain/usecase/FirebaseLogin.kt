package com.example.pokemonapp.domain.usecase

import com.example.pokemonapp.domain.model.User
import com.example.pokemonapp.domain.repository.AuthRepository
import com.example.pokemonapp.domain.repository.UserRepository
import com.example.pokemonapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FirebaseLogin @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(email: String, password: String): Flow<Resource<User>> = flow {
        emit(Resource.Loading)
        val userUID = authRepository.login(email, password)
        if (userUID.isNotEmpty()) {
            val user: User = userRepository.getUser(userUID)
            emit(Resource.Success(user))
            emit(Resource.Finished)
        } else {
            emit(Resource.Error("Login error"))
            emit(Resource.Finished)
        }
    }
}