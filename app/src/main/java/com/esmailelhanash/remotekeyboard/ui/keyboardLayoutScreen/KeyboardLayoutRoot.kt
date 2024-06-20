package com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.esmailelhanash.remotekeyboard.ui.LayoutsViewModel
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.ButtonItem.ButtonItem
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.ButtonItem.EditButtonItem
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.addNewButtonDialog.AddNewButtonDialog
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.changeLayoutBackgroundDialog.ChangeLayoutBackgroundDialog
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.changeShadowDialog.ChangeShadowDialog
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.editDialogs.EditDialogs
import com.esmailelhanash.remotekeyboard.ui.theme.Champagne


@Composable
fun KeyboardLayoutRoot(layoutsViewModel: LayoutsViewModel) {
    val selectedLayout by layoutsViewModel.selectedLayout.observeAsState()
    val editMode by layoutsViewModel.editMode.observeAsState()

    // initiate EditViewModel in the scope of this composable
    val editViewModel : EditViewModel = viewModel()
    val theButtonToEdit by editViewModel.theButtonToEdit.observeAsState()
    val editAction by editViewModel.editAction.observeAsState()
    observeEditAction(editViewModel, layoutsViewModel)

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        content = { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)
                    .fillMaxSize()
                    .background(
                        selectedLayout?.background?.color ?: Champagne
                    )
            ) {
                selectedLayout?.background?.image?.let { imagePath ->
                    val imageUri = "file://$imagePath"
                    Image(
                        painter = rememberImagePainter(imageUri),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(), // Make the image fill the Box
                        contentScale = ContentScale.Crop ,// Adjust the scaling of the image
                    )
                }

                selectedLayout?.keyboardButtons?.forEach { button ->
                    ButtonItem(button = button, selectedLayout = selectedLayout!!, editViewModel = editViewModel){
                        layoutsViewModel.updateButtonInSelectedLayout(it)
                    }
                }

                if (editMode == true){
                    EditButtonItem(
                        editViewModel, selectedLayout?.shadow
                    )
                }
            }
            if (editAction != null && theButtonToEdit != null) {
                EditDialogs(onEditConfirm = {
                    layoutsViewModel.updateButtonInSelectedLayout(it)
                }, editViewModel )
            }
        }
    )
}
@Composable
private fun observeEditAction(
    editViewModel: EditViewModel,
    layoutsViewModel: LayoutsViewModel
) {
    editViewModel.editAction.observeAsState().let {
        when (it.value) {
            EditAction.ADD_NEW_BUTTON -> {
                AddNewButtonDialog(layoutsViewModel) {
                    editViewModel.setEditAction(null)
                }
            }

            EditAction.DRAG -> {}
            EditAction.RESIZE -> {}
            EditAction.RENAME -> {}
            EditAction.CHANGE_ICON -> {}
            EditAction.CHANGE_KEYSTROKE -> {}
            EditAction.CHANGE_COLORS -> {}
            EditAction.CHANGE_BG -> {
                ChangeLayoutBackgroundDialog(layoutsViewModel) {
                    editViewModel.setEditAction(null)
                }
            }

            EditAction.CHANGE_FONT -> {}
            EditAction.CHANGE_FONT_SIZE -> {}
            EditAction.CHANGE_SHADOW -> {
                ChangeShadowDialog(layoutsViewModel) {
                    editViewModel.setEditAction(null)
                }
            }

            null -> {}
        }
    }
}

