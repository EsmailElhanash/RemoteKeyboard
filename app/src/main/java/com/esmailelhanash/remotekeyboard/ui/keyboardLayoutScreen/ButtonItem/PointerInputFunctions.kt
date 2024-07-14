package com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.ButtonItem

import android.util.Log
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.PointerInputScope
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import com.esmailelhanash.remotekeyboard.data.model.KeyboardButton
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.EditAction
import kotlin.math.sqrt

private const val TAG = "PointerInputFunctions"

private var dragStartCorner : DragStartCorner? = null

fun pointerInputHandler(
    confirmEdits: (KeyboardButton) -> Unit,
    button: KeyboardButton,
    liveUpdateButton: (KeyboardButton) -> Unit,
    editAction: LiveData<EditAction?>
): suspend PointerInputScope.() -> Unit = {

    detectDragGestures(
        onDragStart = onDragStart(button,editAction),
        onDrag = onDrag(button, editAction, liveUpdateButton),
        onDragEnd = onDragEnd(button,editAction,confirmEdits)
    )
    
}


private fun PointerInputScope.onDragStart(button: KeyboardButton,editAction: LiveData<EditAction?>): (Offset) -> Unit =
    {
        if (editAction.value == EditAction.RESIZE) {
            detectDragStartCorner(button,it)
        }
    }

private fun onDrag(
    button: KeyboardButton,
    editAction: LiveData<EditAction?>,
    liveUpdateButton: (KeyboardButton) -> Unit
) : (change: PointerInputChange, dragAmount: Offset) -> Unit = { _, dragAmount ->
    if (editAction.value == EditAction.DRAG) {
        liveUpdateButton(
//            button.copy(
//                x = button.x + dragAmount.div(3.0F).x.toInt(),
//                y = button.y + dragAmount.div(3.0F).y.toInt()
//            )
            button.apply{
                x += dragAmount.div(3.0F).x.toInt()
                y += dragAmount.div(3.0F).y.toInt()
            }
        )
    }
    if (editAction.value == EditAction.RESIZE && dragStartCorner != null) {
        val size = IntSize(
            button.width, button.height
        )
        handleDragToResize(dragAmount, size)
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

private fun handleDragToResize(
    dragAmount: Offset,
    size: IntSize
) {
    val myDragAmount = dragAmount.div(5.0F) // Adjust the drag sensitivity if needed
    Log.d("dragStartCorner", dragStartCorner.toString())
    when (dragStartCorner) {
        DragStartCorner.TOP_LEFT -> {
            Log.d(TAG, "ButtonItem: ")
            val newWidth = size.width - myDragAmount.x.toInt()
            val newHeight = size.height - myDragAmount.y.toInt()
            if (newWidth > 10 && newHeight > 10) {
//                updateOffset(offset.plus(Offset(myDragAmount.x, myDragAmount.y)))
//                updateSize(IntSize(newWidth, newHeight))
            }
        }

        DragStartCorner.TOP_RIGHT -> {
            val newWidth = size.width + myDragAmount.x.toInt()
            val newHeight = size.height - myDragAmount.y.toInt()
            if (newWidth > 10 && newHeight > 10) {
//                updateOffset(offset.plus(Offset(0f, myDragAmount.y)))
//                updateSize(IntSize(newWidth, newHeight))
            }
        }

        DragStartCorner.BOTTOM_LEFT -> {
            val newWidth = size.width - myDragAmount.x.toInt()
            val newHeight = size.height + myDragAmount.y.toInt()
            if (newWidth > 10 && newHeight > 10) {
//                updateOffset(offset.plus(Offset(myDragAmount.x, 0f)))
//                updateSize(IntSize(newWidth, newHeight))
            }
        }

        DragStartCorner.BOTTOM_RIGHT -> {
            val newWidth = size.width + myDragAmount.x.toInt()
            val newHeight = size.height + myDragAmount.y.toInt()
            if (newWidth > 10 && newHeight > 10) {
                // No need to change maxOffset for bottom right drag
//                updateSize(IntSize(newWidth, newHeight))
            }
        }

        null -> {}
    }
//    button.width = size.width
//    button.height = size.height
}


private fun PointerInputScope.detectDragStartCorner(button: KeyboardButton
                                            , dragStartPosition : Offset){
    val size = IntSize(
        button.width, button.height
    )
    val position = Offset(
        button.x.toFloat(), button.y.toFloat()
    )
    // Calculate the distance of the drag start point from each corner
    val topLeftDistance = distance(
        dragStartPosition,
        Offset(position.x, position.y)
    )
    val topRightDistance =
        distance(
            dragStartPosition,
            Offset(
                position.x + size.width.dp.toPx(),
                position.y
            )
        )
    val bottomLeftDistance =
        distance(
            dragStartPosition,
            Offset(
                position.x,
                position.y + size.height.dp.toPx()
            )
        )
    val bottomRightDistance = distance(
        dragStartPosition,
        Offset(
            position.x + size.width.dp.toPx(),
            position.y + size.height.dp.toPx()
        )
    )

    // log all distances
    Log.d("CornersDistances", "ButtonItem: topLeftDistance: $topLeftDistance")
    Log.d("CornersDistances", "ButtonItem: topRightDistance: $topRightDistance")
    Log.d("CornersDistances", "ButtonItem: bottomLeftDistance: $bottomLeftDistance")
    Log.d("CornersDistances", "ButtonItem: bottomRightDistance: $bottomRightDistance")


    // Determine the closest corner
    val minDistance = listOf(
        topLeftDistance,
        topRightDistance,
        bottomLeftDistance,
        bottomRightDistance
    ).minOrNull()

//    when (minDistance) {
//        topLeftDistance -> updateDragStartCorner(
//            DragStartCorner.TOP_LEFT
//        )
//
//        topRightDistance -> updateDragStartCorner(
//            DragStartCorner.TOP_RIGHT
//        )
//
//        bottomLeftDistance -> updateDragStartCorner(
//            DragStartCorner.BOTTOM_LEFT
//        )
//
//        bottomRightDistance -> updateDragStartCorner(
//            DragStartCorner.BOTTOM_RIGHT
//        )
//    }
}

private fun distance(point1: Offset, point2: Offset): Float {
    val dx = point2.x - point1.x
    val dy = point2.y - point1.y
    return sqrt(dx * dx + dy * dy)
}