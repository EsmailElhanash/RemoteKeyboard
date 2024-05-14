package com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.editActionsDialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.esmailelhanash.remotekeyboard.ui.common.DialogRoot
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.EditAction
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.EditViewModel
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.getText


// a dialog to edit a keyboard layout, it has a list of actions to perform
// add new button,
//drag,
//resize
@Composable
fun EditModeActionsDialog(
    editViewModel: EditViewModel,
    updateDialogVisibilityState: (Boolean) -> Unit,
) {
    DialogRoot {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .verticalScroll(
                    rememberScrollState()
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ){
            EditAction.entries.forEach { action ->
                ActionItem(action,editViewModel){
                    updateDialogVisibilityState(false)
                }
            }
        }
    }
}

@Composable
private fun ActionItem(action: EditAction, editViewModel: EditViewModel, onClick: () -> Unit) {
    Text(
        text = action.getText(),
        style = MaterialTheme.typography.headlineSmall.copy(textAlign = TextAlign.Center), // Add textAlign here
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                editViewModel.setEditAction(action)
                onClick()
            }
    )
}