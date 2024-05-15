package com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen

import android.util.Log
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
import com.esmailelhanash.remotekeyboard.data.model.KeyboardButton
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.editActionsDialog.EditModeActionsDialog
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.renameDialog.RenameButtonDialog
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.resizeButtonDialog.ResizeButtonDialog
import com.esmailelhanash.remotekeyboard.utils.editModeButton

private const val TAG = "ButtonItem"

@Composable
fun ButtonItem(button: KeyboardButton, editViewModel: EditViewModel, onEditConfirm: (KeyboardButton) -> Unit) {
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
                    Log.d(TAG, "ButtonItem: $button is in drag mode")
                    detectDragGestures(
                        onDragEnd = {
                            onEditConfirm(button.apply {
                                this.x = maxOffset.x.toInt()
                                this.y = maxOffset.y.toInt()
                            })
                        }
                    ) { _, dragAmount ->
                        maxOffset = maxOffset.plus(dragAmount.div(3.0F))
                        button.x = maxOffset.x.toInt()
                        button.y = maxOffset.y.toInt()
                    }

                }
            }.clickable {
                editViewModel.setEditButton(button)
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

    Dialogs( onEditConfirm = onEditConfirm, editViewModel )
}
@Composable
private fun Dialogs(onEditConfirm: (KeyboardButton) -> Unit, editViewModel: EditViewModel) {
   editViewModel.editButton.observeAsState().value?.let { button ->
       when (editViewModel.editAction.value){
           EditAction.RESIZE -> {
               ResizeButtonDialog(
                   button = button,
                   onConfirm = { width, height ->
                       onEditConfirm(button.apply {
                           this.width = width
                           this.height = height
                       })
                       editViewModel.setEditButton(null)
                   },
                   onCancel = {
                       editViewModel.setEditButton(null)
                   }
               )
           }
           EditAction.RENAME -> {
               RenameButtonDialog(
                   button = button,
                   onConfirm = { newName ->
                       onEditConfirm(button.apply {
                           this.name = newName
                       })
                       editViewModel.setEditButton(null)
                   },
                   onCancel = {
                       editViewModel.setEditButton(null)
                   }
               )
           }
           EditAction.CHANGE_ICON -> TODO()
           EditAction.CHANGE_KEYSTROKE -> TODO()
           EditAction.CHANGE_COLORS -> TODO()
           EditAction.CHANGE_BG -> TODO()
           else -> {}
       }


   }

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