package com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.esmailelhanash.remotekeyboard.ui.EditAction
import com.esmailelhanash.remotekeyboard.ui.EditViewModel


// a dialog to edit a keyboard layout, it has a list of actions to perform
// add new button,
//drag,
//resize
@Composable
fun EditModeActionsDialog(
    editViewModel: EditViewModel,
    updateDialogVisibilityState: (Boolean) -> Unit,
) {
    Dialog(
        onDismissRequest = { updateDialogVisibilityState(false) },
        properties = DialogProperties(usePlatformDefaultWidth = true,),

        content = {
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.surface,
                modifier = Modifier.width(300.dp).height(200.dp)
            ){
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .verticalScroll(
                            rememberScrollState()
                        ),
                    verticalArrangement = Arrangement.SpaceBetween
                ){
                    Text(text = "add new button", style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.fillMaxWidth()
                            .clickable {
                                editViewModel.setEditAction(EditAction.ADD_NEW_BUTTON)

                                updateDialogVisibilityState(false)
                            }
                    )
                    Text(text = "drag", style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.fillMaxWidth()
                            .clickable {
                                editViewModel.setEditAction(EditAction.DRAG)
                                updateDialogVisibilityState(false)
                            }
                    )
                    Text(text = "resize", style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.fillMaxWidth()
                            .clickable {
                                editViewModel.setEditAction(EditAction.RESIZE)
                                updateDialogVisibilityState(false)
                            }
                    )
                }
            }
    })
}