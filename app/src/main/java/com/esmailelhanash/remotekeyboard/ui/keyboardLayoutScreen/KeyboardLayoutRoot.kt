package com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.esmailelhanash.remotekeyboard.ui.LayoutsViewModel
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.addNewButtonDialog.AddNewButtonDialog

@Composable
fun KeyboardLayoutRoot(viewModel: LayoutsViewModel) {
    val selectedLayout by viewModel.selectedLayout.observeAsState()
    val editMode by viewModel.editMode.observeAsState()

    // initiate EditViewModel in the scope of this composable
    val editViewModel : EditViewModel = viewModel()

    editViewModel.editAction.observeAsState().let {
        when (it.value) {
            EditAction.ADD_NEW_BUTTON -> {
                AddNewButtonDialog(viewModel){
                    editViewModel.setEditAction(null)
                }
            }
            EditAction.DRAG -> {}
            EditAction.RESIZE -> {}
            EditAction.RENAME -> {}
            EditAction.CHANGE_ICON -> {}
            EditAction.CHANGE_KEYSTROKE -> {}
            EditAction.CHANGE_COLORS -> {}
            EditAction.CHANGE_BG -> {}
            null -> {}
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = selectedLayout?.background?.color ?: MaterialTheme.colorScheme.background),
        content = { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {

                selectedLayout?.keyboardButtons?.forEach { button ->
                    ButtonItem(button = button, editViewModel = editViewModel){
                        viewModel.saveEditedLayout()
                    }
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

