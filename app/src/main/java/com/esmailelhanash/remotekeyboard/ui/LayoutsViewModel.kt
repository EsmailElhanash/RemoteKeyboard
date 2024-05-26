package com.esmailelhanash.remotekeyboard.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esmailelhanash.remotekeyboard.data.model.KeyboardButton
import com.esmailelhanash.remotekeyboard.data.model.KeyboardLayout
import com.esmailelhanash.remotekeyboard.data.model.LayoutBackground
import com.esmailelhanash.remotekeyboard.data.repository.KeyboardLayoutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LayoutsViewModel @Inject constructor(
    private val repository: KeyboardLayoutRepository
) : ViewModel()  {

    // The list of keyboard layouts live data
    private var _layoutsLiveData = MutableLiveData<List<KeyboardLayout>>()
    val layoutsLiveData: LiveData<List<KeyboardLayout>> = _layoutsLiveData

    private val _selectedLayout = MutableLiveData<KeyboardLayout?>()
    val selectedLayout: LiveData<KeyboardLayout?> = _selectedLayout


    private val _editMode = MutableLiveData<Boolean>()
    val editMode: LiveData<Boolean> = _editMode

    fun selectLayout(layout: KeyboardLayout) {
        _selectedLayout.value = layout
    }


    fun setEditMode(editMode: Boolean) {
        _editMode.value = editMode
    }

    // Add a function to get the selected layout based on the ID
    fun getSelectedLayout(): KeyboardLayout? {
        return _layoutsLiveData.value?.find { it == selectedLayout.value }
    }

    // Initialize with an empty list or fetch from a repository
    init {
        _layoutsLiveData.value = emptyList()
        fetchKeyboardLayouts()

    }

    // Example method to add a single layout to the list
    fun addLayout(layout: KeyboardLayout) {
        viewModelScope.launch {
            repository.insertKeyboardLayout(layout)
            val currentList = _layoutsLiveData.value ?: emptyList()
            _layoutsLiveData.postValue(currentList + layout)
        }
    }

    fun updateButtonInSelectedLayout(button: KeyboardButton) {
        val currentSelectedLayout = _selectedLayout.value
        if (currentSelectedLayout!= null) {
            val updatedButtons = currentSelectedLayout.keyboardButtons.map {
                if (it == button) button else it
            }

            // Create a new instance of KeyboardLayout with the updated list of buttons
            val updatedLayout = currentSelectedLayout.copy(keyboardButtons = updatedButtons)

            viewModelScope.launch {
                // Update the layout in the repository
                repository.updateKeyboardLayout(updatedLayout)

                // Optionally, update the LiveData to reflect the change in the UI
                _selectedLayout.postValue(updatedLayout)

                // If you maintain a list of layouts, you might also need to update that
                val updatedLayouts = _layoutsLiveData.value?.map {
                    if (it.id == updatedLayout.id) updatedLayout else it
                } ?: listOf()
                _layoutsLiveData.postValue(updatedLayouts)
            }
        }
    }

    fun changeLayoutShadow(shadow: Int) {
        val currentSelectedLayout = _selectedLayout.value
        if (currentSelectedLayout!= null) {
            // Create a new instance of KeyboardLayout with the updated background
            val updatedLayout = currentSelectedLayout.copy(shadow = shadow)

            viewModelScope.launch {
                // Update the layout in the repository
                repository.updateKeyboardLayout(updatedLayout)

                // Optionally, update the LiveData to reflect the change in the UI
                _selectedLayout.postValue(updatedLayout)

                // If you maintain a list of layouts, you might also need to update that
                val updatedLayouts = _layoutsLiveData.value?.map {
                    if (it.id == updatedLayout.id) updatedLayout else it
                }?: listOf()
                _layoutsLiveData.postValue(updatedLayouts)
            }
        }
    }

    fun changeLayoutBackground(background: LayoutBackground) {
        val currentSelectedLayout = _selectedLayout.value
        if (currentSelectedLayout!= null) {
            // Create a new instance of KeyboardLayout with the updated background
            val updatedLayout = currentSelectedLayout.copy(background = background)

            viewModelScope.launch {
                // Update the layout in the repository
                repository.updateKeyboardLayout(updatedLayout)

                // Optionally, update the LiveData to reflect the change in the UI
                _selectedLayout.postValue(updatedLayout)

                // If you maintain a list of layouts, you might also need to update that
                val updatedLayouts = _layoutsLiveData.value?.map {
                    if (it.id == updatedLayout.id) updatedLayout else it
                }?: listOf()
                _layoutsLiveData.postValue(updatedLayouts)
            }
        }
    }
    fun changeLayoutFont(chosenFont: String?) {
        val currentSelectedLayout = _selectedLayout.value
        if (currentSelectedLayout!= null) {
            // Create a new instance of KeyboardLayout with the updated background
            val updatedLayout = currentSelectedLayout.copy(font = chosenFont)

            viewModelScope.launch {
                // Update the layout in the repository
                repository.updateKeyboardLayout(updatedLayout)

                // Optionally, update the LiveData to reflect the change in the UI
                _selectedLayout.postValue(updatedLayout)

                // If you maintain a list of layouts, you might also need to update that
                val updatedLayouts = _layoutsLiveData.value?.map {
                    if (it.id == updatedLayout.id) updatedLayout else it
                }?: listOf()
                _layoutsLiveData.postValue(updatedLayouts)
            }
        }
    }

    fun addButtonToSelectedLayout(button: KeyboardButton) {
        val currentSelectedLayout = _selectedLayout.value
        if (currentSelectedLayout != null) {
            // Create a new list of buttons by adding the new button to the existing list
            val updatedButtons = currentSelectedLayout.keyboardButtons + button

            // Create a new instance of KeyboardLayout with the updated list of buttons
            val updatedLayout = currentSelectedLayout.copy(keyboardButtons = updatedButtons)

            viewModelScope.launch {
                // Update the layout in the repository
                repository.updateKeyboardLayout(updatedLayout)

                // Optionally, update the LiveData to reflect the change in the UI
                _selectedLayout.postValue(updatedLayout)

                // If you maintain a list of layouts, you might also need to update that
                val updatedLayouts = _layoutsLiveData.value?.map {
                    if (it.id == updatedLayout.id) updatedLayout else it
                } ?: listOf()
                _layoutsLiveData.postValue(updatedLayouts)
            }
        }
    }

    // Example method to fetch keyboard layouts (assuming you have a repository or data source)
    // This is just a placeholder to indicate where you might fetch data from a repository
    private fun fetchKeyboardLayouts() {
        viewModelScope.launch {
            _layoutsLiveData.value = repository.getKeyboardLayouts()
        }
    }


}