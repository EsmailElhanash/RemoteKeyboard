package com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.ButtonItem

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
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.PointerInputScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.esmailelhanash.remotekeyboard.data.model.KeyboardButton
import com.esmailelhanash.remotekeyboard.ui.LayoutsViewModel
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.EditAction
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.EditViewModel
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.editActionsDialog.EditModeActionsDialog
import com.esmailelhanash.remotekeyboard.utils.defaultFont
import com.esmailelhanash.remotekeyboard.utils.editModeButton
import com.esmailelhanash.remotekeyboard.utils.toFontFamily
import com.esmailelhanash.remotekeyboard.utils.toIcon
import kotlin.math.sqrt

private const val TAG = "ButtonItem"

@Composable
fun ButtonItem(button: KeyboardButton
               , editViewModel: EditViewModel
               , layoutsViewModel: LayoutsViewModel
               ,onEditConfirm: (KeyboardButton) -> Unit) {

    val buttonItemViewModel = ButtonItemViewModelFactory(
        buttonItem = button,
        editViewModel = editViewModel,
    ).create(ButtonItemViewModel::class.java)



    ButtonShadow(buttonItemViewModel, layoutsViewModel)
    ButtonContent(buttonItemViewModel, onEditConfirm, editViewModel, layoutsViewModel)

}
@Composable
private fun ButtonContent(
    buttonItemViewModel: ButtonItemViewModel,
    onEditConfirm: (KeyboardButton) -> Unit,
    editViewModel: EditViewModel,
    layoutsViewModel: LayoutsViewModel
) {
    buttonItemViewModel.apply {
        // Directly access the state variables
        val offset by rememberUpdatedState(offset)
        val size by rememberUpdatedState(size)
        val button by rememberUpdatedState(button)

        Box(
            modifier = Modifier
                .offset(x = offset.x.dp, y = offset.y.dp)
                .size(
                    width = size.width.dp,
                    height = size.height.dp
                )
                .border(width = 1.dp, shape = MaterialTheme.shapes.medium, color = button.borderColor)
                .background(color = button.backgroundColor, shape = MaterialTheme.shapes.medium)
                .pointerInput(Unit) {
                    Log.d(TAG, "ButtonItem: $button is in drag mode")
                    detectDragGestures(
                        onDragStart = onDragStart(this@apply),
                        onDrag = onDrag(this@apply),
                        onDragEnd = {
                            onEditConfirm(button.apply {
                                this.x = offset.x.toInt()
                                this.y = offset.y.toInt()
                            })
                        }
                    )
                }.clickable {
                    if (editViewModel.editAction.value != null)
                        editViewModel.setEditButton(button)
                },
            contentAlignment = Alignment.Center
        ) {
            VisibleContent(button, layoutsViewModel)
        }
    }

}

@Composable
private fun VisibleContent(
    button: KeyboardButton,
    layoutsViewModel: LayoutsViewModel
) {
    Column(
        // center items horizontally
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
                fontFamily = layoutsViewModel.selectedLayout.value?.font?.toFontFamily
                    ?: defaultFont.value,
                fontWeight = FontWeight.Normal,
                fontSize = button.fontSize?.sp ?: 16.sp
            ),
            color = button.textColor,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}


private fun onDrag(
    buttonItemViewModel: ButtonItemViewModel
) : (change: PointerInputChange, dragAmount: Offset) -> Unit = { _, dragAmount ->
        buttonItemViewModel.apply {

            if (editAction == EditAction.DRAG) {
                Log.d(TAG, "onDrag: ")
                this.offset = offset.plus(dragAmount.div(3.0F))
                button.x = offset.x.toInt()
                button.y = offset.y.toInt()
            }

            if (editAction == EditAction.RESIZE && dragStartCorner != null) {
                val myDragAmount = dragAmount.div(5.0F) // Adjust the drag sensitivity if needed
                Log.d("dragStartCorner", dragStartCorner.toString())
                when (dragStartCorner) {
                    DragStartCorner.TOP_LEFT -> {
                        Log.d(TAG, "ButtonItem: ")
                        val newWidth = size.width - myDragAmount.x.toInt()
                        val newHeight = size.height - myDragAmount.y.toInt()
                        if (newWidth > 10 && newHeight > 10) {
                            this.offset = offset.plus(Offset(myDragAmount.x, myDragAmount.y))
                            this.size = IntSize(newWidth, newHeight)
                        }
                    }

                    DragStartCorner.TOP_RIGHT -> {
                        val newWidth = size.width + myDragAmount.x.toInt()
                        val newHeight = size.height - myDragAmount.y.toInt()
                        if (newWidth > 10 && newHeight > 10) {
                            this.offset = offset.plus(Offset(0f, myDragAmount.y))
                            this.size = IntSize(newWidth, newHeight)
                        }
                    }

                    DragStartCorner.BOTTOM_LEFT -> {
                        val newWidth = size.width - myDragAmount.x.toInt()
                        val newHeight = size.height + myDragAmount.y.toInt()
                        if (newWidth > 10 && newHeight > 10) {
                            this.offset = offset.plus(Offset(myDragAmount.x, 0f))
                            this.size = IntSize(newWidth, newHeight)
                        }
                    }

                    DragStartCorner.BOTTOM_RIGHT -> {
                        val newWidth = size.width + myDragAmount.x.toInt()
                        val newHeight = size.height + myDragAmount.y.toInt()
                        if (newWidth > 10 && newHeight > 10) {
                            // No need to change maxOffset for bottom right drag
                            this.size = IntSize(newWidth, newHeight)
                        }
                    }

                    null -> {}
                }
                button.width = size.width
                button.height = size.height
            }
        }

    }

private fun PointerInputScope.onDragStart(buttonItemViewModel: ButtonItemViewModel): (Offset) -> Unit =
    {

        buttonItemViewModel.apply{
            if (editAction == EditAction.RESIZE) {
                val dragStartPosition = it
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

                when (minDistance) {
                    topLeftDistance -> dragStartCorner =
                        DragStartCorner.TOP_LEFT

                    topRightDistance -> dragStartCorner =
                        DragStartCorner.TOP_RIGHT

                    bottomLeftDistance -> dragStartCorner =
                        DragStartCorner.BOTTOM_LEFT

                    bottomRightDistance -> dragStartCorner =
                        DragStartCorner.BOTTOM_RIGHT
                }
            }
        }

        // You can store this corner information in a state or variable to use during the drag operation
    }

@Composable
private fun ButtonShadow(
    buttonItemViewModel: ButtonItemViewModel,
    layoutsViewModel: LayoutsViewModel
) {
    buttonItemViewModel.apply{
        val shadow = layoutsViewModel.selectedLayout.value?.shadow?.dp ?: 8.dp
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
}

@Composable
fun EditButtonItem(editViewModel: EditViewModel, layoutsViewModel: LayoutsViewModel) {
    val button = editModeButton
    val editAction by editViewModel.editAction.observeAsState()
    var maxOffset by remember { mutableStateOf(Offset(button.x.toFloat(), button.y.toFloat())) }
    var showEditDialog by remember { mutableStateOf(false) }

    val shadow = layoutsViewModel.selectedLayout.value?.shadow?.dp ?: 8.dp
    val halfShadow = (shadow / 2 * -1)

    Box(
        modifier = Modifier
            .size(width = button.width.dp + shadow, height = button.height.dp + shadow)
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


// Custom function to calculate the distance between two Offset points
private fun distance(point1: Offset, point2: Offset): Float {
    val dx = point2.x - point1.x
    val dy = point2.y - point1.y
    return sqrt(dx * dx + dy * dy)
}

enum class DragStartCorner {
    TOP_LEFT,
    TOP_RIGHT,
    BOTTOM_LEFT,
    BOTTOM_RIGHT
}