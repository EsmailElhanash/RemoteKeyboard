package com.esmailelhanash.remotekeyboard.data.model

import androidx.compose.ui.graphics.Color

data class KeyboardButton(
    val layoutID : Int,
    var name: String,
    var keystroke : String,
    var iconName: String?,
    var x: Int,
    var y: Int,
    var width: Int,
    var height: Int,
    var backgroundColor: Color,
    var borderColor: Color,
    var textColor: Color,
    // todo shadow, as a function of the button color

)
