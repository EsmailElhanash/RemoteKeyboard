package com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.ButtonItem

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.PointerInputScope
import androidx.lifecycle.LiveData
import com.esmailelhanash.remotekeyboard.data.model.KeyboardButton
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.EditAction
import kotlin.math.sqrt

private const val TAG = "PointerInputFunctions"

fun pointerInputHandler(
    confirmEdits: (KeyboardButton) -> Unit,
    button: KeyboardButton,
    liveUpdateButton: (KeyboardButton) -> Unit,
    editAction: LiveData<EditAction?>
): suspend PointerInputScope.() -> Unit = {
    if (editAction.value == EditAction.DRAG){
        detectDragGestures(
            onDrag = onDrag(button, editAction, liveUpdateButton),
            onDragEnd = onDragEnd(button,editAction,confirmEdits)
        )
    }
    
}

private fun onDrag(
    button: KeyboardButton,
    editAction: LiveData<EditAction?>,
    liveUpdateButton: (KeyboardButton) -> Unit
) : (change: PointerInputChange, dragAmount: Offset) -> Unit = { change, dragAmount ->
    if (editAction.value == EditAction.DRAG){
        liveUpdateButton(
            button.apply{
                x += dragAmount.div(3.0F).x.toInt()
                y += dragAmount.div(3.0F).y.toInt()
            }
        )
    }

}

private fun onDragEnd(
    button: KeyboardButton,
    editAction: LiveData<EditAction?>,
    confirmEdits: (KeyboardButton) -> Unit
): () -> Unit = {
    if (editAction.value == EditAction.DRAG){
        confirmEdits(button)
    }
}

private fun distance(point1: Offset, point2: Offset): Float {
    val dx = point2.x - point1.x
    val dy = point2.y - point1.y
    return sqrt(dx * dx + dy * dy)
}