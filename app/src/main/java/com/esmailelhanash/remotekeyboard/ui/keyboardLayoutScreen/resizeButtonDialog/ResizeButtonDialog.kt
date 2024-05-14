package com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.resizeButtonDialog

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import com.esmailelhanash.remotekeyboard.data.model.KeyboardButton
import com.esmailelhanash.remotekeyboard.ui.common.DialogRoot
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.addNewButtonDialog.ActionButtons
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.addNewButtonDialog.FormTextField


/**
// a composable function to show the dialog
 and the function to be called when the dialog is dismissed

 it is a text fields to enter the new width and height of the button
 with a confirm and cancel options

*/
@Composable
fun ResizeButtonDialog(
    button: KeyboardButton,
    onConfirm: (newWidth : Int, newHeight: Int) -> Unit,
    onCancel: () -> Unit,
) {
    var newWidth by remember { mutableStateOf(TextFieldValue()) }
    var newHeight by remember { mutableStateOf(TextFieldValue()) }

    DialogRoot {
        Column {
            // text fields to enter the new width and height of the button
            // with a confirm and cancel options
            FormTextField(
                label = "New Width, old width: ${button.width}",
                value = newWidth,
                onValueChange = {
                    newWidth = it
                },
                keyboardType = KeyboardType.Number
            )

            FormTextField(
                label = "New Height, old height: ${button.height}",
                value = newHeight,
                onValueChange = {
                    newHeight = it
                },
                keyboardType = KeyboardType.Number
            )

            ActionButtons(
                onCancel = {
                    onCancel()
                },
                onConfirm = {
                    try {
                        onConfirm(
                            newWidth.text.toInt(),
                            newHeight.text.toInt()
                        )
                    }catch (e: NumberFormatException) {
                        // show error message
                    }
                }
            )
        }
    }

}