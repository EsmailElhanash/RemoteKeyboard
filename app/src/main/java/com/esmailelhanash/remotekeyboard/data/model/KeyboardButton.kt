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
    var fontSize: Int?,
    val id: String
){

    override fun equals(other: Any?): Boolean {
        // Check if the other object is an instance of KeyboardButton
        if (other !is KeyboardButton) {
            return false
        }

        // Compare the id of the two objects
        return this.id == other.id


    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
