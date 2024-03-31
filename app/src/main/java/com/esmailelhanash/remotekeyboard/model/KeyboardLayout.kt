package com.esmailelhanash.remotekeyboard.model

data class KeyboardLayout(
    val name: String,
    val buttons: List<KeyboardButton>,
    val background: String?,
)
