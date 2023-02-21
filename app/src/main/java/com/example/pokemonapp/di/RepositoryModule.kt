package com.example.pokemonapp.di

import com.example.pokemonapp.data.remote.FirebaseAuthRepositoryImpl
import com.example.pokemonapp.data.remote.RealTimeTeamRepositoryImpl
import com.example.pokemonapp.data.remote.RealTimeUserRepositoryImpl
import com.example.pokemonapp.domain.repository.AuthRepository
import com.example.pokemonapp.domain.repository.TeamRepository
import com.example.pokemonapp.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAuthRepository(firebaseAuthRepositoryImpl: FirebaseAuthRepositoryImpl):
            AuthRepository

    @Binds
    abstract fun bindUserRepository(userRepositoryImpl: RealTimeUserRepositoryImpl):
            UserRepository

    @Binds
    abstract fun bindTeamRepository(teamRepositoryImpl: RealTimeTeamRepositoryImpl):
            TeamRepository

}