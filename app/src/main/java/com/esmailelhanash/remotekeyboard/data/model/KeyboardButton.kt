package com.esmailelhanash.remotekeyboard.data.model

import androidx.compose.ui.graphics.Color

data class KeyboardButton(
    val layoutID : Int,
    val id: Int,
    val name: String,
    val keystroke : String,
    val icon: String,
    val x: Int,
    val y: Int,
    val width: Int,
    val height: Int,
    val color: Color,
    val borderColor: Color,
    val textColor: Color,
)
