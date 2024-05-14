package com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.renameDialog

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import com.esmailelhanash.remotekeyboard.data.model.KeyboardButton
import com.esmailelhanash.remotekeyboard.ui.common.DialogRoot
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.addNewButtonDialog.ActionButtons
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.addNewButtonDialog.FormTextField

@Composable
fun RenameButtonDialog(
    button: KeyboardButton,
    onConfirm: (newName: String) -> Unit,
    onCancel: () -> Unit,
) {
    var newName by remember { mutableStateOf(TextFieldValue()) }
    DialogRoot {
        Column {
            FormTextField(
                label = "New name, old name: ${button.name}",
                value = newName,
                onValueChange = {
                    newName = it
                }
            )
            ActionButtons(
                onCancel = {
                    onCancel()
                },
                onConfirm = {
                    onConfirm(newName.text)
                }
            )
        }
    }
}