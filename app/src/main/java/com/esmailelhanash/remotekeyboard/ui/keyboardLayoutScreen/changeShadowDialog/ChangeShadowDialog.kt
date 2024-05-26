package com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.changeShadowDialog

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
import com.esmailelhanash.remotekeyboard.ui.LayoutsViewModel
import com.esmailelhanash.remotekeyboard.ui.common.DialogRoot
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.ActionButtons
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.FormTextField

@Composable
fun ChangeShadowDialog(
    layoutsViewModel: LayoutsViewModel,
    dismiss: () -> Unit,
) {
    var shadow by remember { mutableStateOf(TextFieldValue()) }
    var showIncorrectInput by remember { mutableStateOf(false) }

    DialogRoot {
        Column {
            FormTextField(
                label = "New shadow, old shadow: ${layoutsViewModel.selectedLayout.value?.shadow}",
                value = shadow,
                onValueChange = {
                    shadow = it
                }
            )
            ActionButtons(
                onCancel = {
                    dismiss()
                },
                onConfirm = {
                    val s = getShadow(shadow)
                    if (s!= null){
                        layoutsViewModel.changeLayoutShadow(
                            shadow = s,
                        )
                        dismiss()
                    }else{
                        showIncorrectInput = true
                    }
                }
            )
        }
    }

    if (showIncorrectInput) {
        IncorrectInputDialog {
            showIncorrectInput = false
        }
    }
}

private fun getShadow(value: TextFieldValue): Int? = value.text.toIntOrNull()

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
