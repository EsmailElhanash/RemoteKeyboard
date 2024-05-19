package com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.changeKeyStrokeDialog

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import com.esmailelhanash.remotekeyboard.data.model.KeyboardButton
import com.esmailelhanash.remotekeyboard.ui.common.DialogRoot
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.ActionButtons
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.FormTextField

@Composable
fun ChangeKeystrokeDialog(
    button: KeyboardButton,
    onConfirm: (keystroke: String) -> Unit,
    onCancel: () -> Unit,
) {
    var keystroke by remember { mutableStateOf(TextFieldValue()) }
    DialogRoot {
        Column {
            FormTextField(
                label = "New keystroke, old keystroke: ${button.keystroke}",
                value = keystroke,
                onValueChange = {
                    keystroke = it
                }
            )
            ActionButtons(
                onCancel = {
                    onCancel()
                },
                onConfirm = {
                    onConfirm(keystroke.text)
                }
            )
        }
    }
}