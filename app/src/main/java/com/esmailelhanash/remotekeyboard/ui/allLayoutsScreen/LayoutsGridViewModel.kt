package com.esmailelhanash.remotekeyboard.ui.allLayoutsScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esmailelhanash.remotekeyboard.data.model.KeyboardLayout
import com.esmailelhanash.remotekeyboard.data.repository.KeyboardLayoutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LayoutsGridViewModel @Inject constructor(
    private val repository: KeyboardLayoutRepository
) : ViewModel()  {
    // The list of keyboard layouts live data
    private var _layoutsLiveData = MutableLiveData<List<KeyboardLayout>>()
    val layoutsLiveData: LiveData<List<KeyboardLayout>> = _layoutsLiveData

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

    // Example method to fetch keyboard layouts (assuming you have a repository or data source)
    // This is just a placeholder to indicate where you might fetch data from a repository
    private fun fetchKeyboardLayouts() {
        viewModelScope.launch {
            _layoutsLiveData.value = repository.getKeyboardLayouts()
        }
    }
}