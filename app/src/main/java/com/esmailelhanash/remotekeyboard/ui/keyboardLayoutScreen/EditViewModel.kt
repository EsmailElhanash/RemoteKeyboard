package com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.esmailelhanash.remotekeyboard.data.model.KeyboardButton

class EditViewModel: ViewModel() {
    private var _editAction = MutableLiveData<EditAction?>()
    val editAction: LiveData<EditAction?> = _editAction

    // a live data for a button being edited, most edit action are applicable to buttons
    // except for add new button, which is a special case
    // and change background, which applies to the whole keyboard layout
    private var _editButton = MutableLiveData<KeyboardButton?>()
    val editButton: LiveData<KeyboardButton?> = _editButton

    fun setEditButton(editButton: KeyboardButton?) {
        _editButton.value = editButton
    }
    fun setEditAction(editAction: EditAction?) {
        _editAction.value = editAction
    }
}
enum class EditAction {
    ADD_NEW_BUTTON ,DRAG, RESIZE, RENAME, CHANGE_ICON, CHANGE_KEYSTROKE, CHANGE_COLORS, CHANGE_BG
}

fun EditAction.getText(): String {
    return when (this) {
        EditAction.ADD_NEW_BUTTON -> "Add new button"
        EditAction.DRAG -> "Drag"
        EditAction.RESIZE -> "Resize"
        EditAction.RENAME -> "Rename"
        EditAction.CHANGE_ICON -> "Change icon"
        EditAction.CHANGE_KEYSTROKE -> "Change keystroke"
        EditAction.CHANGE_COLORS -> "Change colors"
        EditAction.CHANGE_BG -> "Change background"
    }
}

