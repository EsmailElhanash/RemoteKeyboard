package com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.esmailelhanash.remotekeyboard.data.model.KeyboardButton
import com.esmailelhanash.remotekeyboard.ui.LayoutsViewModel
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.editActionsDialog.EditModeActionsDialog
import com.esmailelhanash.remotekeyboard.utils.defaultFont
import com.esmailelhanash.remotekeyboard.utils.editModeButton
import com.esmailelhanash.remotekeyboard.utils.toFontFamily
import com.esmailelhanash.remotekeyboard.utils.toIcon

private const val TAG = "ButtonItem"

@Composable
fun ButtonItem(button: KeyboardButton
               , editViewModel: EditViewModel
               , layoutsViewModel: LayoutsViewModel
               ,onEditConfirm: (KeyboardButton) -> Unit) {
    val editAction by editViewModel.editAction.observeAsState()
    var maxOffset by remember { mutableStateOf(Offset(button.x.toFloat(), button.y.toFloat())) }


    Box(
        modifier = Modifier
            .size(width = button.width.dp + 8.dp, height = button.height.dp + 8.dp)
            .offset((-4).dp + maxOffset.x.dp, (-4).dp + maxOffset.y.dp) // Adjust the offset to control the shadow's direction
            .background(color = button.backgroundColor.copy(alpha = 0.1f), shape = MaterialTheme.shapes.medium) // Customize the shadow color and opacity
    )

    Box(
        modifier = Modifier
            .offset(x = maxOffset.x.dp, y = maxOffset.y.dp)
            .size(width = button.width.dp, height = button.height.dp)
            .border(width = 1.dp, shape = MaterialTheme.shapes.medium, color = button.borderColor)
            .background(color = button.backgroundColor, shape = MaterialTheme.shapes.medium)
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
                if (editViewModel.editAction.value != null)
                    editViewModel.setEditButton(button)
            },
        contentAlignment = Alignment.Center
    ) {

        Column (
            // center items horizontally
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            button.iconName?.toIcon()?.let {
                Icon(
                    imageVector = it,
                    contentDescription = button.name,
                    tint = button.textColor,
                    modifier = Modifier.size(24.dp)
                )
            }
            Text(
                text = button.name,
                style = TextStyle(
                fontFamily = layoutsViewModel.selectedLayout.value?.font?.toFontFamily ?: defaultFont.value,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp
                ),
                color = button.textColor,
                modifier = Modifier.padding(top = 8.dp)
            )
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
            .size(width = button.width.dp + 8.dp, height = button.height.dp + 8.dp)
            .offset((-4).dp + maxOffset.x.dp, (-4).dp + maxOffset.y.dp) // Adjust the offset to control the shadow's direction
            .background(color = button.backgroundColor.copy(alpha = 0.1f), shape = MaterialTheme.shapes.medium) // Customize the shadow color and opacity
    )

    Box(
        modifier = Modifier
            .offset(x = maxOffset.x.dp, y = maxOffset.y.dp)
            .size(width = button.width.dp, height = button.height.dp)
            .background(color = button.backgroundColor, shape = MaterialTheme.shapes.medium)
            .border(width = 1.dp, shape = MaterialTheme.shapes.medium, color = button.borderColor)
            .pointerInput(Unit) {
                detectDragGestures { _, dragAmount ->
                    if (editAction == EditAction.DRAG) {
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