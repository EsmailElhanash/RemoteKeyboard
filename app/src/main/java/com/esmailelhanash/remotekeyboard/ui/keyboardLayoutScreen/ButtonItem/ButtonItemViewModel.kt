package com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.ButtonItem

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.esmailelhanash.remotekeyboard.data.model.KeyboardButton

class ButtonItemViewModel(button: KeyboardButton) : ViewModel() {

    private val _buttonItem = MutableLiveData(button)
    val buttonItem: LiveData<KeyboardButton> = _buttonItem

    // a function to update the button item
    fun updateButton(newButton: KeyboardButton) {
        _buttonItem.value = newButton
    }
}


class ButtonItemViewModelFactory(private val buttonItem: KeyboardButton) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ButtonItemViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ButtonItemViewModel(buttonItem) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}