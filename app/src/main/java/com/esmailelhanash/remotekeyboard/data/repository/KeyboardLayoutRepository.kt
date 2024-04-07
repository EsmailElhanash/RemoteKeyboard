package com.esmailelhanash.remotekeyboard.data.repository


import com.esmailelhanash.remotekeyboard.data.model.KeyboardLayout

interface KeyboardLayoutRepository {
    suspend fun getKeyboardLayouts(): List<KeyboardLayout>
}