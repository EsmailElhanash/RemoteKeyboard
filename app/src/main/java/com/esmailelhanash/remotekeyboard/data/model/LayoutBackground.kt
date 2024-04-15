package com.esmailelhanash.remotekeyboard.data.model

import androidx.compose.ui.graphics.Color


// a Layout background can have a background image or a background color

data class LayoutBackground(
    val color: Color?,
    val image: String?
)
