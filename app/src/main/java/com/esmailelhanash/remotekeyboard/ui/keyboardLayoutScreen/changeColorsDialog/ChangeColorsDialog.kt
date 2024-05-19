package com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.changeColorsDialog

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.esmailelhanash.remotekeyboard.data.model.KeyboardButton
import com.esmailelhanash.remotekeyboard.ui.common.DialogRoot
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.ActionButtons
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.ColorSelectDialog
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.ColorSelectorRow
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.addNewButtonDialog.ColorSelectionType

@Composable
fun ChangeColorsDialog(
    button: KeyboardButton,
    onConfirm: (KeyboardButton) -> Unit,
    onCancel: () -> Unit
) {

    var selectedBorderColor by remember { mutableStateOf(button.borderColor) }
    var selectedTextColor by remember { mutableStateOf(button.textColor) }
    var selectedBGColor by remember { mutableStateOf(button.backgroundColor) }
    var showColorsDialog by remember { mutableStateOf(false) }
    var activeColorSelection : ColorSelectionType? by remember { mutableStateOf(null) }

    DialogRoot {
        Column {
            ColorSelectorRow(
                selectedColor = selectedBGColor,
                prompt = "Button Background Color",
                onBoxClick = {
                    showColorsDialog = true
                    activeColorSelection = ColorSelectionType.BG_COLOR
                },
            )
            ColorSelectorRow(
                selectedColor = selectedTextColor,
                prompt = "Button Text Color",
                onBoxClick = {
                    showColorsDialog = true
                    activeColorSelection = ColorSelectionType.TEXT_COLOR
                },
            )
            ColorSelectorRow(
                selectedColor = selectedBorderColor,
                prompt = "Button Border Color",
                onBoxClick = {
                    showColorsDialog = true
                    activeColorSelection = ColorSelectionType.BORDER_COLOR
                },
            )
            ActionButtons(
                onCancel = {
                    onCancel()
                },
                onConfirm = {
                    onConfirm(
                        button.copy(
                            backgroundColor = selectedBGColor,
                            textColor = selectedTextColor,
                            borderColor = selectedBorderColor
                        )
                    )
                }
            )

        }
    }

    if (showColorsDialog){
        ColorSelectDialog(
            onColorSelected = {
                when (activeColorSelection) {
                    ColorSelectionType.BG_COLOR -> selectedBGColor = it
                    ColorSelectionType.TEXT_COLOR -> selectedTextColor = it
                    ColorSelectionType.BORDER_COLOR -> selectedBorderColor = it
                    else -> throw IllegalStateException("No active color selection")
                }
                showColorsDialog = false
            }
        )
    }
}