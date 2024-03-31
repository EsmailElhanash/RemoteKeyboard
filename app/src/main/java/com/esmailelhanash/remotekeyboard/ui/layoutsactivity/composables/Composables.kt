package com.esmailelhanash.remotekeyboard.ui.layoutsactivity.composables

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.esmailelhanash.remotekeyboard.model.KeyboardButton
import com.esmailelhanash.remotekeyboard.model.KeyboardLayout
import com.esmailelhanash.remotekeyboard.ui.theme.RemoteKeyboardTheme


@Composable
fun Fab() {
    FloatingActionButton(
        onClick = {
            // show dialog to add new keyboard layout

        },
        modifier = Modifier.padding(16.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = null
        )
    }
}
@Composable
fun Root(modifier: Modifier = Modifier) {
    KeyboardLayoutsGrid(
        keyboardLayouts = listOf(
            KeyboardLayout(
                name = "QWERTY",
                buttons = listOf(
                    KeyboardButton(name = "Q",
                        icon = Icons.Default.Add.toString(),
                        x = 0,
                        y = 0,
                        width = 100,
                        height = 100,
                        color = "#000000",
                        textColor = "#FFFFFF",
                    )
                ),
                background = null
            )
        )
    )
}

// a function to show a dialog to add new keyboard layout
fun showAddKeyboardLayoutDialog() {

}





@Preview(showBackground = true)
@Composable
fun RootPreview() {
    RemoteKeyboardTheme {
        Root()
    }
}