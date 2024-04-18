package com.esmailelhanash.remotekeyboard.utils

import com.esmailelhanash.remotekeyboard.data.model.KeyboardButton

object DefaultButtonsConfiguration {

    fun addNewButtonButton(): KeyboardButton {
        return KeyboardButton(
            layoutID = -1,
            id = -1,
            name = "Add new button",
            keystroke = "b",
            icon = "",
            x = 0,
            y = 0,
            width = 0,
            height = 0,
            color = "DefaultColor", // Placeholder value for color
            borderColor = "DefaultBorderColor", // Placeholder value for borderColor
            textColor = "DefaultTextColor" // Placeholder value for textColor
        )
    }
}