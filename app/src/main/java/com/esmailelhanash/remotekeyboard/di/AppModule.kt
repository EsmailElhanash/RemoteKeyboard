package com.esmailelhanash.remotekeyboard.di


import com.esmailelhanash.remotekeyboard.data.repository.KeyboardLayoutRepository
import com.esmailelhanash.remotekeyboard.data.repository.impl.KeyboardLayoutRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // Choose the appropriate Hilt component
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindKeyboardLayoutRepository(
        repositoryImpl: KeyboardLayoutRepositoryImpl
    ): KeyboardLayoutRepository
}