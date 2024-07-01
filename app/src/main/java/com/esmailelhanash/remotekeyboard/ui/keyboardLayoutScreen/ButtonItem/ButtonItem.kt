package com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.ButtonItem

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
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.esmailelhanash.remotekeyboard.data.model.KeyboardButton
import com.esmailelhanash.remotekeyboard.data.model.KeyboardLayout
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.EditAction
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.EditViewModel
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.editActionsDialog.EditModeActionsDialog
import com.esmailelhanash.remotekeyboard.utils.defaultFont
import com.esmailelhanash.remotekeyboard.utils.editModeButton
import com.esmailelhanash.remotekeyboard.utils.toFontFamily
import com.esmailelhanash.remotekeyboard.utils.toIcon

private const val TAG = "ButtonItem"


@Composable
fun ButtonItem(button: KeyboardButton
               , editViewModel: EditViewModel
               , selectedLayout: KeyboardLayout
               , onEditConfirm: (KeyboardButton) -> Unit) {



    // button state and a function to update it
    var mButton by remember { mutableStateOf(button) }
    fun updateButton(button: KeyboardButton) {
        mButton = button
    }

    Box{
        ButtonShadow(mButton, selectedLayout)
        ButtonRoot(mButton, ::updateButton, onEditConfirm, editViewModel, selectedLayout)
    }
}
@Composable
private fun ButtonRoot(
    button: KeyboardButton,
    updateButtonState : (KeyboardButton) -> Unit,
    confirmEdits: (KeyboardButton) -> Unit,
    editViewModel: EditViewModel,
    keyboardLayout: KeyboardLayout
) {
    Box(
        modifier = Modifier
            .offset(x = button.x.dp, y = button.y.dp)
            .size(
                width = button.width.dp,
                height = button.height.dp
            )
            .border(
                width = 1.dp,
                shape = MaterialTheme.shapes.medium,
                color = button.borderColor
            )
            .background(color = button.backgroundColor, shape = MaterialTheme.shapes.medium)
            .pointerInput(
                key1 = Unit,
                block = pointerInputHandler(
                    confirmEdits,
                    button,
                    updateButtonState,
                    editViewModel.editAction
                )
            )
            .clickable {
                if (editViewModel.editAction.value != null)
                    editViewModel.setEditButton(button)
            },
        contentAlignment = Alignment.Center
    ) {
        VisibleContent(button, keyboardLayout)
    }

}



@Composable
private fun VisibleContent(
    button: KeyboardButton,
    keyboardLayout: KeyboardLayout
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
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
                fontFamily = keyboardLayout.font?.toFontFamily
                    ?: defaultFont.value,
                fontWeight = FontWeight.Normal,
                fontSize = button.fontSize?.sp ?: 16.sp
            ),
            color = button.textColor,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}


@Composable
private fun ButtonShadow(
    button: KeyboardButton,
    selectedLayout: KeyboardLayout
) {
    val offset = Offset(
        button.x.toFloat(), button.y.toFloat()
    )
    val size = IntSize(
        width = button.width,
        height = button.height
    )
    val shadow = selectedLayout.shadow?.dp ?: 8.dp
    val halfShadow = (shadow / 2 * -1)
    Box(
        modifier = Modifier
            .size(
                width = size.width.dp + shadow,
                height = size.height.dp + shadow
            )
            .offset(
                (halfShadow) + offset.x.dp,
                halfShadow + offset.y.dp
            ) // Adjust the offset to control the shadow's direction
            .background(
                color = button.backgroundColor.copy(alpha = 0.1f),
                shape = MaterialTheme.shapes.medium
            ) // Customize the shadow color and opacity
    )

}

@Composable
fun EditButtonItem(editViewModel: EditViewModel, shadow: Int?) {
    val button = editModeButton
    val editAction by editViewModel.editAction.observeAsState()
    var maxOffset by remember { mutableStateOf(Offset(button.x.toFloat(), button.y.toFloat())) }
    var showEditDialog by remember { mutableStateOf(false) }

    val mShadow = shadow?.dp ?: 8.dp
    val halfShadow = (mShadow / 2 * -1)

    Box(
        modifier = Modifier
            .size(width = button.width.dp + mShadow, height = button.height.dp + mShadow)
            .offset((halfShadow) + maxOffset.x.dp, halfShadow + maxOffset.y.dp) // Adjust the offset to control the shadow's direction
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


enum class DragStartCorner {
    TOP_LEFT,
    TOP_RIGHT,
    BOTTOM_LEFT,
    BOTTOM_RIGHT
}