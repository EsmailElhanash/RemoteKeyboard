package com.esmailelhanash.remotekeyboard.data.model

import androidx.compose.ui.graphics.Color

data class KeyboardButton(
    val id: String,
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
    var fontSize: Int?,
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is KeyboardButton) return false

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
