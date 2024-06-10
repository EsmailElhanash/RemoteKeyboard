package com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.ButtonItem

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntSize
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.esmailelhanash.remotekeyboard.data.model.KeyboardButton
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.EditAction
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.EditViewModel

class ButtonItemViewModel(var button : KeyboardButton, editViewModel: EditViewModel) : ViewModel() {



    var editAction : EditAction? = null

    var offset by mutableStateOf(Offset(button.x.toFloat(), button.y.toFloat()))

    var size by mutableStateOf(IntSize(button.width, button.height))

    val position by mutableStateOf(Offset(button.x.toFloat(), button.y.toFloat()))

    var dragStartCorner : DragStartCorner? by mutableStateOf(null)

    init {
        editViewModel.editAction.observeForever {
            editAction = it
        }
    }
}


class ButtonItemViewModelFactory(private val buttonItem: KeyboardButton,
    private val editViewModel: EditViewModel) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ButtonItemViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ButtonItemViewModel(buttonItem, editViewModel ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}