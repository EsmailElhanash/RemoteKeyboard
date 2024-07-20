package com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.PointerInputScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.esmailelhanash.remotekeyboard.data.model.KeyboardButton
import com.esmailelhanash.remotekeyboard.ui.LayoutsViewModel
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.ButtonItem.ButtonItem
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.ButtonItem.EditButtonItem
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.addNewButtonDialog.AddNewButtonDialog
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.changeLayoutBackgroundDialog.ChangeLayoutBackgroundDialog
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.changeShadowDialog.ChangeShadowDialog
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.editDialogs.EditDialogs
import com.esmailelhanash.remotekeyboard.ui.theme.Champagne
import kotlin.math.sqrt


@Composable
fun KeyboardLayoutRoot(layoutsViewModel: LayoutsViewModel) {
    val selectedLayout by layoutsViewModel.selectedLayout.observeAsState()
    val editMode by layoutsViewModel.editMode.observeAsState()

    // initiate EditViewModel in the scope of this composable
    val editViewModel : EditViewModel = viewModel()
    val theButtonToEdit by editViewModel.theButtonToEdit.observeAsState()

    var theButtonToResize by remember { mutableStateOf<KeyboardButton?>(null) }

    val buttonDimensions = remember(theButtonToResize) {
        val button = theButtonToResize
        if (button != null) {
            Dimensions(button.x, button.y, button.width, button.height)
        } else {
            Dimensions(0, 0, 0, 0)
        }
    }


    val editAction by editViewModel.editAction.observeAsState()

    var dragStartCorner by remember {
        mutableStateOf<DragStartCorner?>(null)
    }

    val density = LocalDensity.current

    val resizeModifier = if (editAction == EditAction.RESIZE)
    {
        Modifier.pointerInput(Unit){
            detectDragGestures(
                onDragStart = {
                    with(density) {
                        selectedLayout?.keyboardButtons?.reversed()?.forEach { button ->
                            // Convert button properties to pixels
                            val buttonXInPx = button.x.dp.toPx()
                            val buttonYInPx = button.y.dp.toPx()
                            val buttonWidthInPx = button.width.dp.toPx() + 10.dp.toPx()
                            val buttonHeightInPx = button.height.dp.toPx() + 10.dp.toPx()

                            // Log the values
                            Log.d("ButtonProperties", "Button X in Px: $buttonXInPx")
                            Log.d("ButtonProperties", "Button Y in Px: $buttonYInPx")
                            Log.d("ButtonProperties", "Button Width in Px: $buttonWidthInPx")
                            Log.d("ButtonProperties", "Button Height in Px: $buttonHeightInPx")

                            val touchPosition = it

                            // Use the pixel values in your formula
                            val liesInXScope =
                                touchPosition.x in buttonXInPx..buttonXInPx + buttonWidthInPx
                            val liesInYScope =
                                touchPosition.y in buttonYInPx..buttonYInPx + buttonHeightInPx



                            Log.d("touchPosition", "x: ${touchPosition.x} y: ${touchPosition.y}")
                            Log.d("TouchScope", "Lies in X Scope: $liesInXScope")
                            Log.d("TouchScope", "Lies in Y Scope: $liesInYScope")

                            if (liesInXScope && liesInYScope) {
                                // set the button to resize
                                // log true
                                Log.d("EditAction.RESIZE", "true")
                                dragStartCorner = null
                                theButtonToResize = button
                                detectDragStartCorner(
                                    button,
                                    it
                                ) {
                                    dragStartCorner = it
                                    // print drag start corner
                                    Log.d("drag_start_corner", "$it")
                                }
                                return@forEach
                            }
                        }
                    }
                },
                onDrag = onDrag@{ change, dragAmount ->
                    change.consume()

                    handleDragToResize(
                        dragAmount,
                        theButtonToResize ?: return@onDrag,
                        IntSize(
                            editViewModel.theButtonToEdit.value?.width ?: return@onDrag,
                            editViewModel.theButtonToEdit.value?.height ?: return@onDrag
                        ),
                        dragStartCorner ?: return@onDrag
                    ){
                        theButtonToResize = it
                    }
                },

                onDragEnd = {
                    theButtonToResize?.let { layoutsViewModel.updateButtonInSelectedLayout(it) }
                    theButtonToResize = null
                }
            )
        }
    } else Modifier

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        content = { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)
                    .fillMaxSize()
                    .background(
                        selectedLayout?.background?.color ?: Champagne
                    )
                .then(
                    resizeModifier
                )
            ) {
                selectedLayout?.background?.image?.let { imagePath ->
                    val imageUri = "file://$imagePath"
                    Image(
                        painter = rememberImagePainter(imageUri),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(), // Make the image fill the Box
                        contentScale = ContentScale.Crop ,// Adjust the scaling of the image
                    )
                }

                selectedLayout?.keyboardButtons?.forEach { button ->
                    ButtonItem(button = button
                        , editViewModel,selectedLayout!!
                        , dimensions = if (button == theButtonToResize)
                            buttonDimensions else null){
                            layoutsViewModel.updateButtonInSelectedLayout(it)
                        }
                }

                if (editMode == true){
                    EditButtonItem(
                        editViewModel, selectedLayout?.shadow
                    )
                }
            }
            if (editAction != null && theButtonToEdit != null) {
                EditDialogs(onEditConfirm = {
                    layoutsViewModel.updateButtonInSelectedLayout(it)
                }, editViewModel )
            }
        }
    )

    EditActionsDialogs(editViewModel, layoutsViewModel)
}

@Composable
private fun EditActionsDialogs(
    editViewModel: EditViewModel,
    layoutsViewModel: LayoutsViewModel
) {
    editViewModel.editAction.observeAsState().let {
        when (it.value) {
            EditAction.ADD_NEW_BUTTON -> {
                AddNewButtonDialog(layoutsViewModel) {
                    editViewModel.setEditAction(null)
                }
            }

            EditAction.DRAG -> {}
            EditAction.RESIZE -> {}
            EditAction.RENAME -> {}
            EditAction.CHANGE_ICON -> {}
            EditAction.CHANGE_KEYSTROKE -> {}
            EditAction.CHANGE_COLORS -> {}
            EditAction.CHANGE_BG -> {
                ChangeLayoutBackgroundDialog(layoutsViewModel) {
                    editViewModel.setEditAction(null)
                }
            }

            EditAction.CHANGE_FONT -> {}
            EditAction.CHANGE_FONT_SIZE -> {}
            EditAction.CHANGE_SHADOW -> {
                ChangeShadowDialog(layoutsViewModel) {
                    editViewModel.setEditAction(null)
                }
            }

            null -> {}
        }
    }
}

private fun handleDragToResize(
    dragAmount: Offset,
    button: KeyboardButton,
    oldSize: IntSize,
    dragStartCorner: DragStartCorner,
    updateButton:  (KeyboardButton) -> Unit
) {
    val myDragAmount = dragAmount.div(5.0F) // Adjust the drag sensitivity if needed
    Log.d("dragStartCorner", dragStartCorner.toString())
    when (dragStartCorner) {
        DragStartCorner.TOP_LEFT -> {
            val newWidth = oldSize.width - myDragAmount.x.toInt()
            val newHeight = oldSize.height - myDragAmount.y.toInt()
            if (newWidth > 10 && newHeight > 10) {

                updateButton(
                    button.apply {
                        x += myDragAmount.x.toInt()
                        y += myDragAmount.y.toInt()
                        width = newWidth
                        height = newHeight
                    }
                )
            }
        }

        DragStartCorner.TOP_RIGHT -> {
            val newWidth = oldSize.width + myDragAmount.x.toInt()
            val newHeight = oldSize.height - myDragAmount.y.toInt()
            if (newWidth > 10 && newHeight > 10) {

                updateButton(
                    button.apply {
                        y += myDragAmount.y.toInt()
                        width = newWidth
                        height = newHeight
                    }
                )
            }
        }

        DragStartCorner.BOTTOM_LEFT -> {
            val newWidth = oldSize.width - myDragAmount.x.toInt()
            val newHeight = oldSize.height + myDragAmount.y.toInt()
            if (newWidth > 10 && newHeight > 10) {
                updateButton(
                    button.apply {
                        x += myDragAmount.x.toInt()
                        width = newWidth
                        height = newHeight
                    }
                )
            }
        }

        DragStartCorner.BOTTOM_RIGHT -> {
            val newWidth = oldSize.width + myDragAmount.x.toInt()
            val newHeight = oldSize.height + myDragAmount.y.toInt()
            if (newWidth > 10 && newHeight > 10) {
                updateButton(
                    button.apply {
                        width = newWidth
                        height = newHeight
                    }
                )
            }
        }
    }

}


private fun PointerInputScope.detectDragStartCorner(button: KeyboardButton
                                                    , dragStartPosition : Offset,
                                                    updateDragStartCorner: (DragStartCorner) -> Unit
                                                    ){
    // Calculate the distance of the drag start point from each corner
    val topLeftDistance = distance(
        dragStartPosition,
        Offset(button.x.dp.toPx(), button.y.dp.toPx())
    )
    val topRightDistance =
        distance(
            dragStartPosition,
            Offset(
                button.x.dp.toPx() + button.width.dp.toPx(),
                button.y.dp.toPx()
            )
        )
    val bottomLeftDistance =
        distance(
            dragStartPosition,
            Offset(
                button.x.dp.toPx(),
                button.y + button.height.dp.toPx()
            )
        )
    val bottomRightDistance = distance(
        dragStartPosition,
        Offset(
            button.x.dp.toPx() + button.width.dp.toPx(),
            button.y.dp.toPx() + button.height.dp.toPx()
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

    // update the drag start corner based on the min distance
    when (minDistance) {
        topLeftDistance -> updateDragStartCorner(DragStartCorner.TOP_LEFT)
        topRightDistance -> updateDragStartCorner(DragStartCorner.TOP_RIGHT)
        bottomLeftDistance -> updateDragStartCorner(DragStartCorner.BOTTOM_LEFT)
        bottomRightDistance -> updateDragStartCorner(DragStartCorner.BOTTOM_RIGHT)
        else -> {}
    }
    // log drag start corner
    when (minDistance){
        topLeftDistance  -> Log.d("dragStartCorner", DragStartCorner.TOP_LEFT.toString())
        topRightDistance -> Log.d("dragStartCorner", DragStartCorner.TOP_RIGHT.toString())
        bottomLeftDistance -> Log.d("dragStartCorner", DragStartCorner.BOTTOM_LEFT.toString())
        bottomRightDistance -> Log.d("dragStartCorner", DragStartCorner.BOTTOM_RIGHT.toString())
    }
}
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

data class Dimensions(val x: Int, val y: Int, val width: Int, val height: Int)

