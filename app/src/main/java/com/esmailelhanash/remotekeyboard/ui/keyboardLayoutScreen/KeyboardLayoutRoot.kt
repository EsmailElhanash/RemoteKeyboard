package com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.esmailelhanash.remotekeyboard.data.model.KeyboardButton
import com.esmailelhanash.remotekeyboard.ui.EditAction
import com.esmailelhanash.remotekeyboard.ui.EditViewModel
import com.esmailelhanash.remotekeyboard.ui.LayoutsViewModel
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.addNewButtonDialog.AddNewButtonDialog
import com.esmailelhanash.remotekeyboard.utils.editModeButton

@Composable
fun KeyboardLayoutRoot(viewModel: LayoutsViewModel) {
    val selectedLayout by viewModel.selectedLayout.observeAsState()
    val editMode by viewModel.editMode.observeAsState()

    // initiate EditViewModel in the scope of this composable
    val editViewModel : EditViewModel = viewModel()
    val editAction = editViewModel.editAction.observeAsState()
    editAction.value?.let {
        when (it) {
            EditAction.ADD_NEW_BUTTON -> {
                AddNewButtonDialog(viewModel){
                    editViewModel.setEditAction(
                        EditAction.NULL
                    )
                }
            }
            EditAction.DRAG -> {
//                DragKeyboardButtonDialog(editViewModel)
            }
            EditAction.RESIZE -> {
//                ResizeKeyboardButtonDialog(editViewModel)
            }

            EditAction.NULL -> {

            }
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = selectedLayout?.background?.color ?: MaterialTheme.colorScheme.background),
        content = { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {

                selectedLayout?.keyboardButtons?.forEach { button ->
                    ButtonItem(button = button, editAction = editAction.value?: EditAction.NULL)
                }


                if (editMode == true){
                    EditButtonItem(
                        editViewModel
                    )
                }
            }
        }
    )
}

@Composable
fun ButtonItem(button: KeyboardButton,editAction: EditAction) {

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
                    }
                }
            }

                ,
        contentAlignment = Alignment.Center
    ) {


        Text(
            text = button.name,
            style = MaterialTheme.typography.bodyMedium,
            color = button.textColor,
            modifier = Modifier.padding(top = 8.dp)

        )
    }
}

@Composable
private fun EditButtonItem(editViewModel: EditViewModel) {
    val isEditActionInProgress = editViewModel.editAction.observeAsState().value != EditAction.NULL
    val button = editModeButton
    var showEditDialog by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .offset(x = button.x.dp, y = button.y.dp)
            .size(width = button.width.dp, height = button.height.dp)
            .background(color = button.backgroundColor, shape = MaterialTheme.shapes.medium)
            .border(width = 1.dp, shape = MaterialTheme.shapes.medium, color = button.borderColor),
        contentAlignment = Alignment.Center
    ) {
        Text(text = if (isEditActionInProgress) "Save" else button.name,
            style = MaterialTheme.typography.bodyMedium,
            color = button.textColor,
            modifier = Modifier.padding(8.dp).clickable {

                if (isEditActionInProgress) {
//                    editViewModel.setEditAction(EditAction.NULL)
                } else {
//                    editViewModel.setEditAction(EditAction.ADD_NEW_BUTTON)
                    showEditDialog = true
                }


            })
    }

    if (showEditDialog){
        EditModeActionsDialog(editViewModel){
            showEditDialog = it
        }
    }
}