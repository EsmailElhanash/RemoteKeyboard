package com.esmailelhanash.remotekeyboard.utils

import androidx.compose.ui.graphics.Color
import com.esmailelhanash.remotekeyboard.data.model.KeyboardButton

const val editButtonId = -1

val editModeButton: KeyboardButton =
     KeyboardButton(
            layoutID = editButtonId,
            id = -1,
            name = "Edit Layout",
            keystroke = "null",
            icon = "",
            x = 70,
            y = 70,
            width = 70,
            height = 70,
            backgroundColor = Color.LightGray, // Placeholder value for color
            borderColor = Color.Black, // Placeholder value for borderColor
            textColor = Color.Black // Placeholder value for textColor
        )




