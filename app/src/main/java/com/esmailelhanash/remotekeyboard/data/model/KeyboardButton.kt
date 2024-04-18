package com.esmailelhanash.remotekeyboard.data.model

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
    val color: String,
    val borderColor: String,
    val textColor: String,
)
