package com.esmailelhanash.remotekeyboard.ui.layoutfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.esmailelhanash.remotekeyboard.data.model.KeyboardLayout

class LayoutFragmentViewModel(private val keyboardLayout: KeyboardLayout) : ViewModel() {

    private var _layout = MutableLiveData<KeyboardLayout>().apply {
        value = keyboardLayout
    }
    val layout: LiveData<KeyboardLayout> = _layout

}