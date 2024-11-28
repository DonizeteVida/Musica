package com.any.musica.common.di

import com.any.musica.common.data.repository.MusicRepositoryImpl
import com.any.musica.common.domain.repository.MusicRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindsMusicRepositoryImpl(
        impl: MusicRepositoryImpl
    ): MusicRepository
}