package com.esmailelhanash.remotekeyboard.data.repository


import com.esmailelhanash.remotekeyboard.data.model.KeyboardButton
import com.esmailelhanash.remotekeyboard.data.model.KeyboardLayout

interface KeyboardLayoutRepository {
    suspend fun getKeyboardLayouts(): List<KeyboardLayout>

    // insert keyboard layout
    suspend fun insertKeyboardLayout(keyboardLayout: KeyboardLayout)

    // update keyboard layout
    suspend fun updateKeyboardLayout(keyboardLayout: KeyboardLayout)

    suspend fun updateLayoutButtons(layout: KeyboardLayout, buttons: List<KeyboardButton>)
}