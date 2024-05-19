package com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.changeLayoutBackgroundDialog

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.esmailelhanash.remotekeyboard.data.model.LayoutBackground
import com.esmailelhanash.remotekeyboard.ui.LayoutsViewModel
import com.esmailelhanash.remotekeyboard.ui.common.DialogRoot
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.ActionButtons
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.ColorSelectDialog
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.ColorSelectorRow
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.ImageSelectorRow

@Composable
fun ChangeLayoutBackgroundDialog(layoutsViewModel: LayoutsViewModel, dismiss: () -> Unit) {
    var showColorsDialog by remember { mutableStateOf(false) }
    var startImagePicker by remember { mutableStateOf(false) }
    var selectedBackgroundColor : Color? by remember { mutableStateOf(
        layoutsViewModel.selectedLayout.value?.background?.color
    ) }
    var selectedBackgroundImagePath : String? by remember { mutableStateOf(
        layoutsViewModel.selectedLayout.value?.background?.image
    ) }

    // Initialize the launcher at the top level of your composable function
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val imagePath = result.data?.data.toString()
            selectedBackgroundImagePath = imagePath
            startImagePicker = false // Reset the flag after picking
        }
    }

    DialogRoot {
        Column {
            ColorSelectorRow(
                selectedColor = selectedBackgroundColor,
                prompt = "Background Color",
                onBoxClick = {
                    showColorsDialog = true
                },
            )

            ImageSelectorRow(
                selectedImagePath = selectedBackgroundImagePath,
                prompt = "Background Image: ",
                clearSelectedImage = {
                    selectedBackgroundImagePath = null
                    startImagePicker = false // Reset the flag after clearing the image path
                },
                onBoxClick = {
                    startImagePicker = true
                }
            )

            ActionButtons(
                onCancel = {
                    dismiss()
                },
                onConfirm = {
                    layoutsViewModel.changeLayoutBackground(
                        background = LayoutBackground(
                            image = selectedBackgroundImagePath,
                            color = selectedBackgroundColor
                        )
                    )
                    dismiss()
                }
            )
        }
    }
    if (showColorsDialog){
        ColorSelectDialog(
            onColorSelected = {
                selectedBackgroundColor = it
                showColorsDialog = false
            }
        )
    }

    // Launch the image picker if the flag is set
    if (startImagePicker) {
        val pickImageIntent = Intent(Intent.ACTION_PICK).apply {
            type = "image/*"
        }
        imagePickerLauncher.launch(pickImageIntent)
    }
}