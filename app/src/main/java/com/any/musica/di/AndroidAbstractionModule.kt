package com.any.musica.di

import com.any.musica.data.android.ContentResolverQuery
import com.any.musica.data.android.ContentResolverQueryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AndroidAbstractionModule {
    @Binds
    fun bindsContentResolverQueryImpl(
        impl: ContentResolverQueryImpl
    ): ContentResolverQuery
}