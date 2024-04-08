package com.esmailelhanash.remotekeyboard.data.repository.impl

import com.esmailelhanash.remotekeyboard.data.database.AppDatabase
import com.esmailelhanash.remotekeyboard.data.database.DatabaseClient
import com.esmailelhanash.remotekeyboard.data.model.KeyboardLayout
import com.esmailelhanash.remotekeyboard.data.repository.KeyboardLayoutRepository

// KeyboardLayoutRepositoryImpl:
class KeyboardLayoutRepositoryImpl(
    private val appDatabase: AppDatabase
) : KeyboardLayoutRepository {
    override suspend fun getKeyboardLayouts(): List<KeyboardLayout> =
        appDatabase.keyboardLayoutDao().getAllLayouts()

}