package com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.esmailelhanash.remotekeyboard.data.model.KeyboardButton
import com.esmailelhanash.remotekeyboard.ui.LayoutsViewModel
import com.esmailelhanash.remotekeyboard.utils.editModeButton

@Composable
fun KeyboardLayoutRoot(viewModel: LayoutsViewModel) {
    val selectedLayout by viewModel.selectedLayout.observeAsState()
    val editMode by viewModel.editMode.observeAsState()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = selectedLayout?.background?.color ?: MaterialTheme.colorScheme.background),
        content = { innerPadding ->
            BoxWithConstraints(modifier = Modifier.padding(innerPadding)) {
                if (editMode == true){
                    ButtonItem(editModeButton)
                }
                selectedLayout?.keyboardButtons?.forEach { button ->
                    ButtonItem(button = button)
                }
            }
        }
    )
}

@Composable
fun ButtonItem(button: KeyboardButton) {
    Box(
        modifier = Modifier
            .offset(x = button.x.dp, y = button.y.dp)
            .size(width = button.width.dp, height = button.height.dp)
            .background(color = button.color)
            .border(width = 1.dp, color = button.borderColor),
        contentAlignment = Alignment.Center
    ) {
        // Assuming you have a function to parse color strings to Color objects
        Text(text = button.name, style = MaterialTheme.typography.bodyLarge, color = button.textColor)

    }
}
