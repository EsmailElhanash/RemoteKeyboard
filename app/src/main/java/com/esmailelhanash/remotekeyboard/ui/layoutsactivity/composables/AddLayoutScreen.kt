package com.esmailelhanash.remotekeyboard.ui.layoutsactivity.composables

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.esmailelhanash.remotekeyboard.ui.theme.RemoteKeyboardTheme

@Composable
fun AddLayoutScreen() {
    // State to manage whether the dialog is showing or not
    val showDialog = remember { mutableStateOf(false) }

    // Button to open the dialog
    Button(onClick = { showDialog.value = true }) {
        Text("Show Dialog")
    }

    // The Dialog
    if (showDialog.value) {
        // This is the default dialog
        Dialog(onDismissRequest = { showDialog.value = false }) {
            // Your dialog content here
            AlertDialog(
                onDismissRequest = { showDialog.value = false },
                title = { Text("Dialog Title") },
                text = { Text("This is the content of the dialog.") },
                confirmButton = {
                    TextButton(onClick = { showDialog.value = false }) {
                        Text("Confirm")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog.value = false }) {
                        Text("Dismiss")
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddLayoutScreenPreview() {
    RemoteKeyboardTheme {
        AddLayoutScreen()
    }
}
