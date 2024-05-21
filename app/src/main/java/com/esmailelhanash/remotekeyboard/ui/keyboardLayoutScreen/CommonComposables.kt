package com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.rememberImagePainter
import com.esmailelhanash.remotekeyboard.ui.theme.Champagne
import com.esmailelhanash.remotekeyboard.utils.colorsList
import com.esmailelhanash.remotekeyboard.utils.iconsList


private const val TAG =  "CommonComposable"
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
fun ImageSelectorRow(selectedImagePath: String?, prompt: String, clearSelectedImage: () -> Unit, onBoxClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = prompt,
            style = MaterialTheme.typography.bodyLarge
        )

        Icon(
            imageVector = Icons.Filled.Edit,
            contentDescription = "Button Icon",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .size(32.dp)
                .clickable { onBoxClick() }
        )

        if (selectedImagePath != null) {
            val imageUri = "file://$selectedImagePath"
            // Display the selected image
            Image(
                painter = rememberImagePainter(imageUri){
                     listener(
                             onError = { request, throwable ->
                                 Log.d(TAG, "Error loading image: $throwable")
                             }
                         )

                },
                contentDescription = "Selected Image",
                modifier = Modifier.size(32.dp),
                contentScale = ContentScale.Crop
            )

            Icon(
                imageVector = Icons.Filled.Clear,
                contentDescription = "clear",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                   .size(32.dp)
                   .clickable {
                       clearSelectedImage()
                   }
            )

        }
    }
}

@Preview
@Composable
private fun ImageSelectorRowPreview() {
    ImageSelectorRow(
        selectedImagePath = null,
        prompt = "Add Image: ",
        clearSelectedImage = {},
        onBoxClick = {}
    )
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


@Composable
fun IconSelectDialog(onIconSelected: (ImageVector) -> Unit) {
    Dialog(
        onDismissRequest = {},
        properties = DialogProperties(usePlatformDefaultWidth = false),
        content = {
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.surface,
                modifier = Modifier.fillMaxWidth(0.6F).fillMaxHeight(0.9F)
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(80.dp),
                    modifier = Modifier.padding(8.dp)
                ) {
                    items(iconsList.size) { index ->
                        val icon = iconsList[index]
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            modifier = Modifier
                                .size(48.dp)
                                .clickable {
                                    onIconSelected(icon)
                                }
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun ColorSelectDialog(onColorSelected: (Color) -> Unit) {
    Dialog(
        onDismissRequest = {},
        properties = DialogProperties(usePlatformDefaultWidth = false),
        content = {
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.surface,
                modifier = Modifier.fillMaxWidth(0.6F).fillMaxHeight(0.9F)
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(80.dp),
                    modifier = Modifier.padding(8.dp)
                ) {
                    items(colorsList.size) { index ->
                        val color = colorsList[index]
                        Box(
                            modifier = Modifier
                                .padding(8.dp)
                                .size(32.dp)
                                .background(color)
                                .clickable {
                                    onColorSelected(color)
                                }
                        )
                    }
                }
            }
        }
    )
}