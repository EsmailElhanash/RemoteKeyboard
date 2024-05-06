package com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.addNewButtonDialog

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.esmailelhanash.remotekeyboard.ui.theme.Champagne


@Composable
fun DialogTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    )
}

@Composable
fun IconSelectorRow(selectedIcon: ImageVector?, onIconClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Add Icon: ",
            style = MaterialTheme.typography.bodyLarge
        )
        Icon(
            imageVector = selectedIcon ?: Icons.Default.AddCircle,
            contentDescription = "Button Icon",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .size(32.dp)
                .clickable { onIconClick() }
        )
    }
}

@Composable
fun ColorSelectorRow(selectedColor: Color?, prompt: String, onBoxClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = prompt,
            style = MaterialTheme.typography.bodyLarge
        )

        // small box to display selected color:
        ColorDisplay(selectedColor ?: Champagne, onClick = onBoxClick)
    }
}

@Composable
private fun ColorDisplay(color: Color, size: Int = 32, onClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .size(size.dp)
            .background(color)
            .border(
                border = BorderStroke(1.dp,Color.Gray),
            )
            .clickable {
                onClick()
            }
    )
    // No content inside, just displaying the color
}

// a preview for ColorDisplay:
@Composable
@Preview
fun ColorDisplayPreview() {
    ColorDisplay(color = Champagne)
}

@Composable
fun FormTextField(value: TextFieldValue, label: String, onValueChange: (TextFieldValue) -> Unit, keyboardType: KeyboardType = KeyboardType.Text) {
    TextField(
        value = value,
        label = { Text(text = label) },
        onValueChange = onValueChange,
        modifier = Modifier.padding(16.dp).fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )
}

@Composable
fun ActionButtons(onCancel: () -> Unit, onConfirm: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextButton(onClick = onCancel) {
            Text("Cancel")
        }
        Spacer(modifier = Modifier.width(8.dp))
        Button(onClick = onConfirm) {
            Text("Confirm")
        }
    }
}
