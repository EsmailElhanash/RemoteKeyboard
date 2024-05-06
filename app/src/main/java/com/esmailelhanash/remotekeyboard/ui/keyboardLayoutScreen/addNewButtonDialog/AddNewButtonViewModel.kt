package com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.addNewButtonDialog

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel

class AddButtonViewModel : ViewModel() {
    var showIconsDialog by mutableStateOf(false)
    var showColorsDialog by mutableStateOf(false)
    var inCompleteFields by mutableStateOf(false)
    var noSelectedLayout by mutableStateOf(false)

    var buttonName by mutableStateOf(TextFieldValue(""))
    var keyStroke by mutableStateOf(TextFieldValue(""))
    var width by mutableStateOf(TextFieldValue(""))
    var height by mutableStateOf(TextFieldValue(""))
    var selectedIcon: ImageVector? by mutableStateOf(null)
    var selectedBGColor: Color? by mutableStateOf(null)
    var selectedTextColor: Color? by mutableStateOf(null)
    var selectedBorderColor: Color? by mutableStateOf(null)

    var activeColorSelection by mutableStateOf(ColorSelectionType.NONE)

    fun checkFormCorrectness(): Boolean {
        return buttonName.text.isNotEmpty() && keyStroke.text.isNotEmpty() && width.text.isNotEmpty() && height.text.isNotEmpty()
    }

    fun resetStates() {
        showIconsDialog = false
        showColorsDialog = false
        inCompleteFields = false
        noSelectedLayout = false
        buttonName = TextFieldValue("")
        keyStroke = TextFieldValue("")
        width = TextFieldValue("")
        height = TextFieldValue("")
        selectedIcon = null
        selectedBGColor = null
        selectedTextColor = null
        selectedBorderColor = null
    }

    fun setSelectedColor(color: Color) {
        when (activeColorSelection) {
            ColorSelectionType.BG_COLOR -> selectedBGColor = color
            ColorSelectionType.TEXT_COLOR -> selectedTextColor = color
            ColorSelectionType.BORDER_COLOR -> selectedBorderColor = color
            ColorSelectionType.NONE -> throw IllegalStateException("No active color selection")
        }
    }
}

enum class ColorSelectionType {
    BG_COLOR,
    TEXT_COLOR,
    BORDER_COLOR,
    NONE
}