package com.esmailelhanash.remotekeyboard.di

import android.content.Context
import androidx.room.Room
import com.esmailelhanash.remotekeyboard.data.dao.KeyboardLayoutDao
import com.esmailelhanash.remotekeyboard.data.database.AppDatabase
import com.esmailelhanash.remotekeyboard.data.database.MIGRATION_1_2
import com.esmailelhanash.remotekeyboard.data.database.MIGRATION_2_3
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    private const val DBNAME = "keyboard-layouts-database"

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, DBNAME)
            .addMigrations(MIGRATION_1_2)
            .addMigrations(MIGRATION_2_3)
            .build()

    @Provides
    @Singleton
    fun provideKeyboardLayoutDao(appDatabase: AppDatabase): KeyboardLayoutDao =
        appDatabase.keyboardLayoutDao()
}