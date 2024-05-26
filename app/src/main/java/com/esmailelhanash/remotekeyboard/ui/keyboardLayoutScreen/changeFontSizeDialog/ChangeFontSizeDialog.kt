package com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.changeFontSizeDialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.esmailelhanash.remotekeyboard.data.model.KeyboardButton
import com.esmailelhanash.remotekeyboard.ui.common.DialogRoot
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.ActionButtons
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.FormTextField

@Composable
fun ChangeFontSizeDialog(
    button: KeyboardButton,
    onConfirm: (newFontSize: Int) -> Unit,
    onCancel: () -> Unit,
) {
    var fontSize by remember { mutableStateOf(TextFieldValue()) }
    var showIncorrectInput by remember { mutableStateOf(false) }

    DialogRoot {
        Column {

            FormTextField(
                label = "New font size, old font size: ${button.fontSize}",
                value = fontSize,
                onValueChange = {
                    fontSize = it
                })

            ActionButtons(
                onCancel = {
                    onCancel()
                },
                onConfirm = {
                    val size = getFontSize(fontSize)
                    if (size!= null) {
                        onConfirm(size)
                    }else{
                        showIncorrectInput = true
                    }

                }
            )
        }
        if (showIncorrectInput){
            IncorrectInputDialog{
                showIncorrectInput = false
            }
        }
    }
}

private fun getFontSize(value: TextFieldValue): Int? = value.text.toIntOrNull()

@Composable
private fun IncorrectInputDialog(
    onConfirm: () -> Unit,
) {
    DialogRoot {
        Column {
            Text(
                text = "Please enter a number",
                modifier = Modifier.padding(16.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(onClick = onConfirm) {
                    Text("OK")
                }
            }
        }
    }

}