package com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.esmailelhanash.remotekeyboard.data.model.KeyboardButton
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.editActionsDialog.EditModeActionsDialog
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.renameDialog.RenameButtonDialog
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.resizeButtonDialog.ResizeButtonDialog
import com.esmailelhanash.remotekeyboard.utils.editModeButton


class DialogsViewModel : ViewModel() {
    var resizeDialogVisible by  mutableStateOf(false)
    var renameDialogVisible by mutableStateOf(false)
    var changeIconDialogVisible by mutableStateOf(false)
    var changeColorsDialogVisible by mutableStateOf(false)
    var changeBgDialogVisible by mutableStateOf(false)
    var changeKeystrokeDialogVisible by mutableStateOf(false)
}
@Composable
fun ButtonItem(button: KeyboardButton, editViewModel: EditViewModel, onEditConfirm: (KeyboardButton) -> Unit) {

    val dialogsViewModel : DialogsViewModel = viewModel()


    val editAction by editViewModel.editAction.observeAsState()
    var maxOffset by remember { mutableStateOf(Offset(button.x.toFloat(), button.y.toFloat())) }
    Box(
        modifier = Modifier
            .offset(x = maxOffset.x.dp, y = maxOffset.y.dp)
            .size(width = button.width.dp, height = button.height.dp)
            .background(color = button.backgroundColor, shape = MaterialTheme.shapes.medium)
            .border(width = 1.dp, shape = MaterialTheme.shapes.medium, color = button.borderColor)
            .pointerInput(Unit) {
                if (editAction == EditAction.DRAG) {
                    detectDragGestures { _, dragAmount ->
                        maxOffset = maxOffset.plus(dragAmount.div(3.0F))
                        button.x = maxOffset.x.toInt()
                        button.y = maxOffset.y.toInt()
                    }
                }
            }.clickable {
                when (editAction) {
                    EditAction.RESIZE -> {
                        dialogsViewModel.resizeDialogVisible = true
                    }
                    EditAction.RENAME -> {
                        dialogsViewModel.renameDialogVisible = true
                    }
                    EditAction.CHANGE_ICON -> {
                        dialogsViewModel.changeIconDialogVisible = true
                    }
                    EditAction.CHANGE_KEYSTROKE -> {
                        dialogsViewModel.changeKeystrokeDialogVisible = true
                    }
                    EditAction.CHANGE_COLORS -> {
                        dialogsViewModel.changeColorsDialogVisible = true
                    }
                    EditAction.CHANGE_BG -> {
                        dialogsViewModel.changeBgDialogVisible = true
                    }

                    else -> {}
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = button.name,
            style = MaterialTheme.typography.bodyMedium,
            color = button.textColor,
            modifier = Modifier.padding(top = 8.dp)

        )
    }

    Dialogs(button = button, onEditConfirm = onEditConfirm, dialogsViewModel = dialogsViewModel)
}
@Composable
private fun Dialogs(button: KeyboardButton, onEditConfirm: (KeyboardButton) -> Unit, dialogsViewModel: DialogsViewModel) {
    if (dialogsViewModel.resizeDialogVisible) {
        ResizeButtonDialog(
            button = button,
            onConfirm = { width, height ->
                onEditConfirm(button.apply {
                    this.width = width
                    this.height = height
                })
                dialogsViewModel.resizeDialogVisible = false
            },
            onCancel = {
                dialogsViewModel.resizeDialogVisible = false
            }
        )
    }else if (dialogsViewModel.renameDialogVisible) {
        RenameButtonDialog(
            button = button,
            onConfirm = { newName ->
                onEditConfirm(button.apply {
                    this.name = newName
                })
                dialogsViewModel.renameDialogVisible = false
            },
            onCancel = {
                dialogsViewModel.renameDialogVisible = false
            }
        )
    }
    else if (dialogsViewModel.changeIconDialogVisible) {}
    else if (dialogsViewModel.changeKeystrokeDialogVisible) {}
    else if (dialogsViewModel.changeColorsDialogVisible) {}
    else if (dialogsViewModel.changeBgDialogVisible) {}
    else if (dialogsViewModel.changeIconDialogVisible) {}

}


@Composable
fun EditButtonItem(editViewModel: EditViewModel) {
    val button = editModeButton
    val editAction by editViewModel.editAction.observeAsState()
    var maxOffset by remember { mutableStateOf(Offset(button.x.toFloat(), button.y.toFloat())) }
    var showEditDialog by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .offset(x = maxOffset.x.dp, y = maxOffset.y.dp)
            .size(width = button.width.dp, height = button.height.dp)
            .background(color = button.backgroundColor, shape = MaterialTheme.shapes.medium)
            .border(width = 1.dp, shape = MaterialTheme.shapes.medium, color = button.borderColor)
            .pointerInput(Unit) {
                if (editAction == EditAction.DRAG) {
                    detectDragGestures { _, dragAmount ->
                        maxOffset = maxOffset.plus(dragAmount.div(3.0F))
                        button.x = maxOffset.x.toInt()
                        button.y = maxOffset.y.toInt()
                    }
                }
            }.clickable {
                if (editAction != null) {
                    editViewModel.setEditAction(null)
                } else {
                    showEditDialog = true
                }


            },
        contentAlignment = Alignment.Center
    ) {
        Text(text = if (editAction != null) "Save" else button.name,
            style = MaterialTheme.typography.bodyMedium,
            color = button.textColor,
            modifier = Modifier.padding(8.dp)
        )
    }

    if (showEditDialog){
        EditModeActionsDialog(editViewModel){
            showEditDialog = it
        }
    }
}