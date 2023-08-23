package com.example.assesmenttest.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {
    @DefaultDispatcher
    @Provides
    fun providesDefaultDispatcher(): CoroutineScope = CoroutineScope(
        Dispatchers.Default
    )

    @IoDispatcher
    @Provides
    fun providesIODispatcher(): CoroutineScope = CoroutineScope(
        Dispatchers.IO
    )

    @MainDispatcher
    @Provides
    fun providesMainDispatcher(): CoroutineScope = CoroutineScope(
        Dispatchers.Main
    )
}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DefaultDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainDispatcher