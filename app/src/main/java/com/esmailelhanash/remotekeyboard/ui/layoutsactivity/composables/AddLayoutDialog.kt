package com.esmailelhanash.remotekeyboard.ui.layoutsactivity.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.esmailelhanash.remotekeyboard.ui.theme.RemoteKeyboardTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


// fun for the dialog, with a callback parameter to set the showDialog state
@Composable
fun AddLayoutDialog(
    updateDialogVisibilityState: (Boolean) -> Unit
) {
    val context = LocalContext.current
    Dialog(
        onDismissRequest = { updateDialogVisibilityState(false) },
        properties = DialogProperties(usePlatformDefaultWidth = true,),

        content = {
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.surface,
//                elevation = 8.dp,
                modifier = Modifier
                    .width(300.dp) // specify width
                    .height(200.dp) // specify height
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Add new keyboard layout",
                        style = MaterialTheme.typography.headlineSmall
                    )
                    var textState by remember { mutableStateOf(TextFieldValue("")) }
                    TextField(
                        value = textState,
                        label = {
                                Text(text = "Layout name")
                        },
                        onValueChange = { textState = it },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        TextButton(
                            onClick = {
                                updateDialogVisibilityState(false)
                            }
                        ) {
                            Text("Cancel")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            onClick = {
                                 /** make a CoroutineScope launch that should inform the user after success or failure of
                                 the insert keyboard layout request result with a Toast */



                                CoroutineScope(
                                    Dispatchers.IO
                                ).launch {
                                    // todo add the keyboard layout to the database

                                        updateDialogVisibilityState(false)
                                    }

                            },
                        ) {
                            Text("Confirm", color = Color.White)
                        }
                    }
                }
            }
        }
    )

}

@Preview(showBackground = true)
@Composable
fun AddLayoutScreenPreview() {
    RemoteKeyboardTheme {
        AddLayoutDialog{
            // do nothing
        }
    }
}
