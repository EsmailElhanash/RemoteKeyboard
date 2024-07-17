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
    val editAction by editViewModel.editAction.observeAsState()

    var dragStartCorner by remember {
        mutableStateOf<DragStartCorner?>(null)
    }
    editViewModel.editAction.observeAsState().let {
        when (it.value) {
            EditAction.ADD_NEW_BUTTON -> {
                AddNewButtonDialog(layoutsViewModel){
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
                ChangeLayoutBackgroundDialog(layoutsViewModel){
                    editViewModel.setEditAction(null)
                }
            }
            EditAction.CHANGE_FONT -> {}
            EditAction.CHANGE_FONT_SIZE -> {}
            EditAction.CHANGE_SHADOW -> {
                ChangeShadowDialog(layoutsViewModel){
                    editViewModel.setEditAction(null)
                }
            }
            null -> {}
        }
    }

    val resizeModifier = if (editAction == EditAction.RESIZE)
    {
        Modifier.pointerInput(Unit){
            detectDragGestures(
                onDragStart = {
                    selectedLayout?.keyboardButtons?.reversed()?.forEach { button ->
                        val liesInXScope = it.x in button.x.dp.toPx()..button.x.dp.toPx() + button.width.dp.toPx()
                        val liesInYScope = it.y in button.y.dp.toPx()..button.y.dp.toPx() + button.height.dp.toPx()

                        if (liesInXScope && liesInYScope) {
                            // set the button to resize
                            // log true
                            Log.d("EditAction.RESIZE", "true")
                            dragStartCorner = null
                            editViewModel.setEditButton(button)
                            detectDragStartCorner(
                                button,
                                it
                            ){
                                dragStartCorner = it
                                // print drag start corner
                                Log.d("drag_start_corner", "$it")
                            }
                            return@forEach
                        }
                    }
                },
                onDrag = onDrag@{ change, dragAmount ->
                    change.consume()
                    // update the button's size and position
                    // based on the drag amount
                    handleDragToResize(
                        dragAmount,
                        IntSize(
                            editViewModel.theButtonToEdit.value?.width ?: return@onDrag,
                            editViewModel.theButtonToEdit.value?.height ?: return@onDrag
                        ),
                        dragStartCorner ?: return@onDrag
                    )
                },

                onDragEnd = {

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
                    ButtonItem(button = button, editViewModel,selectedLayout!!){
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
}

private fun handleDragToResize(
    dragAmount: Offset,
    size: IntSize,
    dragStartCorner: DragStartCorner
) {
    val myDragAmount = dragAmount.div(5.0F) // Adjust the drag sensitivity if needed
    Log.d("dragStartCorner", dragStartCorner.toString())
    when (dragStartCorner) {
        DragStartCorner.TOP_LEFT -> {
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