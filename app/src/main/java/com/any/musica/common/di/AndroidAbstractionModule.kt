package com.any.musica.common.di

import com.any.musica.common.data.android.ContentResolverQuery
import com.any.musica.common.data.android.ContentResolverQueryImpl
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