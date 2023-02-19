package com.example.pokemonapp.data.remote

import android.util.Log
import com.example.pokemonapp.domain.repository.AuthRepository
import com.example.pokemonapp.utils.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseAuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
): AuthRepository {

    override suspend fun login(email: String, password: String): String {
        return try {
            var userId: String = ""
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener { userId = it.user?.uid ?: ""}
                .await()
            userId
        } catch (e: Exception) {
            Log.d("test", e.toString())
            ""
        }
    }

    override suspend fun signup(email: String, password: String): String {
        return try {
            var userId: String = ""
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener { userId = it.user?.uid ?: "" }
                .await()
            userId
        } catch (e: Exception) {
            ""
        }
    }

}