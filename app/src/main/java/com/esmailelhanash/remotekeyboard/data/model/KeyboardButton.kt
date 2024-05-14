package com.esmailelhanash.remotekeyboard.data.model

import androidx.compose.ui.graphics.Color

data class KeyboardButton(
    val layoutID : Int,
    val id: Int,
    var name: String,
    val keystroke : String,
    val icon: String,
    var x: Int,
    var y: Int,
    var width: Int,
    var height: Int,
    val backgroundColor: Color,
    val borderColor: Color,
    val textColor: Color,
    // todo shadow, as a function of the button color

)
