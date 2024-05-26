package com.esmailelhanash.remotekeyboard.ui.allLayoutsScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.esmailelhanash.remotekeyboard.data.model.KeyboardButton
import com.esmailelhanash.remotekeyboard.data.model.KeyboardLayout
import com.esmailelhanash.remotekeyboard.data.model.LayoutBackground
import com.esmailelhanash.remotekeyboard.data.repository.KeyboardLayoutRepository
import com.esmailelhanash.remotekeyboard.ui.LayoutsViewModel
import com.esmailelhanash.remotekeyboard.ui.common.DialogRoot
import com.esmailelhanash.remotekeyboard.ui.theme.Champagne
import com.esmailelhanash.remotekeyboard.utils.defaultFont
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


// fun for the dialog, with a callback parameter to set the showDialog state
@Composable
fun AddLayoutDialog(
    viewModel: LayoutsViewModel,
    updateDialogVisibilityState: (Boolean) -> Unit,
) {
    DialogRoot {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = "Add new keyboard layout",
                style = MaterialTheme.typography.headlineSmall
            )
            //spacer:
            Spacer(modifier = Modifier.height(16.dp))

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
                        CoroutineScope(
                            Dispatchers.IO
                        ).launch {
                            viewModel.addLayout(
                                KeyboardLayout(
                                    name = textState.text,
                                    keyboardButtons = listOf(),
                                    background = LayoutBackground(
                                        color = Champagne,
                                        image = null
                                    ),
                                    font = defaultFont.key,
                                    shadow = 4
                                )
                            )

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


// preview:
@Composable
@Preview
fun AddLayoutDialogPreview() {
    AddLayoutDialog(
        viewModel = mockLayoutsViewModel(),
        updateDialogVisibilityState = { }
    )
}

// mock layouts viewmodel
@Composable
fun mockLayoutsViewModel() =
    LayoutsViewModel(mockKeyboardLayoutRepository())


private fun mockKeyboardLayoutRepository(): KeyboardLayoutRepository {

    // return some mock data
    return object : KeyboardLayoutRepository {
        override suspend fun getKeyboardLayouts(): List<KeyboardLayout> {
            return listOf()
        }

        override suspend fun insertKeyboardLayout(keyboardLayout: KeyboardLayout) {

        }

        override suspend fun updateKeyboardLayout(keyboardLayout: KeyboardLayout) {

        }

        override suspend fun updateLayoutButtons(
            layout: KeyboardLayout,
            buttons: List<KeyboardButton>
        ) {

        }
    }
}