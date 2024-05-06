package com.esmailelhanash.remotekeyboard.data.model

import androidx.compose.ui.graphics.Color

data class KeyboardButton(
    val layoutID : Int,
    val id: Int,
    val name: String,
    val keystroke : String,
    val icon: String,
    var x: Int,
    val y: Int,
    val width: Int,
    val height: Int,
    val backgroundColor: Color,
    val borderColor: Color,
    val textColor: Color,
    // todo shadow, as a function of the button color

)
