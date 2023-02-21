package com.example.pokemonapp.data.remote

import com.example.pokemonapp.data.model.User
import com.example.pokemonapp.domain.repository.UserRepository
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RealTimeUserRepositoryImpl @Inject constructor(
    private val realtime: FirebaseDatabase
): UserRepository{

    override suspend fun createUser(user: User): Boolean {
        return try {
            var isSuccessful = false
            realtime.getReference("users")
                .child(user.userId).setValue(user)
                .addOnCompleteListener { isSuccessful = it.isSuccessful }
                .await()
            isSuccessful
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun getUser(userId: String): User {
        return try {
            var loggedUser = User()
            realtime.getReference("users")
                .child(userId).get()
                .addOnSuccessListener {
                    loggedUser = it.getValue<User>() as User
                }
                .await()
            loggedUser
        } catch (e: Exception) {
            User()
        }
    }

}