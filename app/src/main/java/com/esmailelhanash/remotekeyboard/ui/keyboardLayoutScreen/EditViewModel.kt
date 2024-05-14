package com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EditViewModel: ViewModel() {
    private var _editAction = MutableLiveData<EditAction?>()
    val editAction: LiveData<EditAction?> = _editAction

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

