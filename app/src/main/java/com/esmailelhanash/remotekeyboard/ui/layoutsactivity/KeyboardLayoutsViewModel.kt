package com.esmailelhanash.remotekeyboard.ui.layoutsactivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.esmailelhanash.remotekeyboard.data.model.KeyboardLayout
import com.esmailelhanash.remotekeyboard.data.repository.KeyboardLayoutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
@HiltViewModel
class KeyboardLayoutsViewModel @Inject constructor(
    private val repository: KeyboardLayoutRepository
) : ViewModel()  {
    // The list of keyboard layouts live data
    private var _layoutsLiveData = MutableLiveData<List<KeyboardLayout>>()
    val layoutsLiveData: LiveData<List<KeyboardLayout>> = _layoutsLiveData

    // Initialize with an empty list or fetch from a repository
    init {
        _layoutsLiveData.value = emptyList()
        fetchKeyboardLayouts()
        // If you have a repository, you might want to fetch the layouts from there
        // fetchKeyboardLayouts()

    }

    // Example method to update the list of keyboard layouts
    fun updateLayouts(layouts: List<KeyboardLayout>) {
        _layoutsLiveData.value = layouts
    }

    // Example method to add a single layout to the list
    fun addLayout(layout: KeyboardLayout) {
        val currentList = _layoutsLiveData.value ?: emptyList()
        _layoutsLiveData.value = currentList + layout
    }

    // Example method to fetch keyboard layouts (assuming you have a repository or data source)
    // This is just a placeholder to indicate where you might fetch data from a repository
    private fun fetchKeyboardLayouts() {
        runBlocking {
            _layoutsLiveData.value = repository.getKeyboardLayouts()
        }
    }
}