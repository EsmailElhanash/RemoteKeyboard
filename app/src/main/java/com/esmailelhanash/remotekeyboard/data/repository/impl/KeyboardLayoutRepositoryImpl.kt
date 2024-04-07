package com.esmailelhanash.remotekeyboard.data.repository.impl

import com.esmailelhanash.remotekeyboard.data.model.KeyboardLayout
import com.esmailelhanash.remotekeyboard.data.repository.KeyboardLayoutRepository

class KeyboardLayoutRepositoryImpl : KeyboardLayoutRepository {
    override suspend fun getKeyboardLayouts(): List<KeyboardLayout> {
        // This is where you would fetch data from a database or a remote server
        // For demonstration, returning a static list
        return listOf(
            KeyboardLayout("Layout 1", "Description for Layout 1"),
            KeyboardLayout("Layout 2", "Description for Layout 2")
            // Add more layouts as needed
        )
    }
}