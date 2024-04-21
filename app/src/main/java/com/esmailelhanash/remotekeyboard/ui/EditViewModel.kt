package com.esmailelhanash.remotekeyboard.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EditViewModel: ViewModel()  {
    private var _editAction = MutableLiveData<EditAction>().apply {
        value = EditAction.NULL
    }
    val editAction: LiveData<EditAction> = _editAction

    fun setEditAction(editAction: EditAction) {
        _editAction.value = editAction
    }


}

enum class EditAction {
    DRAG, ADD_NEW_BUTTON, RESIZE, NULL
}