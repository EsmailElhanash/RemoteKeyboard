package com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.esmailelhanash.remotekeyboard.ui.LayoutsViewModel

@Composable
fun KeyboardLayoutRoot(viewModel: LayoutsViewModel, editMode: Boolean = false) {
    val selectedLayout by viewModel.selectedLayout.observeAsState()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = selectedLayout?.background?.color ?: MaterialTheme.colorScheme.background),
        content = { innerPadding ->
            Box(
                modifier = Modifier.padding(innerPadding)
            )
        }
    )
}