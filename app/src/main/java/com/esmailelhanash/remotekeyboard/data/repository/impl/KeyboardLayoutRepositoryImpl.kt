package com.esmailelhanash.remotekeyboard.data.repository.impl

import com.esmailelhanash.remotekeyboard.data.dao.KeyboardLayoutDao
import com.esmailelhanash.remotekeyboard.data.model.KeyboardLayout
import com.esmailelhanash.remotekeyboard.data.repository.KeyboardLayoutRepository
import javax.inject.Inject

// KeyboardLayoutRepositoryImpl:
class KeyboardLayoutRepositoryImpl @Inject constructor(
    private val keyboardLayoutDao: KeyboardLayoutDao
) : KeyboardLayoutRepository {
    override suspend fun getKeyboardLayouts(): List<KeyboardLayout> {
        return keyboardLayoutDao.getAllLayouts()
    }
}