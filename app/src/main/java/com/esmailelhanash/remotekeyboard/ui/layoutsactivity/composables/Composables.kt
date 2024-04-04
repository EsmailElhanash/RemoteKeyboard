package com.esmailelhanash.remotekeyboard.ui.layoutsactivity.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.esmailelhanash.remotekeyboard.data.model.KeyboardButton
import com.esmailelhanash.remotekeyboard.data.model.KeyboardLayout
import com.esmailelhanash.remotekeyboard.ui.theme.RemoteKeyboardTheme


@Composable
fun Fab() {
    var showDialog by remember { mutableStateOf(false) }
    FloatingActionButton(
        onClick = { showDialog = true }, // Set the state to true to show the dialog
        modifier = Modifier.padding(16.dp)

    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = null
        )
    }
    if (showDialog) {
        AddLayoutDialog {
            showDialog = it
        }
    }
}

@Composable
fun Root(modifier: Modifier = Modifier) {
//    KeyboardLayoutsGrid(
//        keyboardLayouts = listOf(
//            KeyboardLayout(
//                name = "QWERTY",
//                buttons = listOf(
//                    KeyboardButton(name = "Q",
//                        icon = Icons.Default.Add.toString(),
//                        x = 0,
//                        y = 0,
//                        width = 100,
//                        height = 100,
//                        color = "#000000",
//                        textColor = "#FFFFFF",
//                    )
//                ),
//                background = null
//            )
//        )
//    )
}




@Preview(showBackground = true)
@Composable
fun RootPreview() {
    RemoteKeyboardTheme {
        Root()
    }
}