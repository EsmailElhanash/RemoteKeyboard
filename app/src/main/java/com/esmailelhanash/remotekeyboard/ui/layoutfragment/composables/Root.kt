package com.esmailelhanash.remotekeyboard.ui.layoutfragment.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.esmailelhanash.remotekeyboard.ui.layoutfragment.LayoutFragmentViewModel

// composable function for the root view of the keyboard layout fragment

@Composable
fun KeyboardLayoutRoot(viewModel:LayoutFragmentViewModel) {
    MaterialTheme {
        // Your Composable goes here
        Box {
            // Example content, replace with your actual UI
            Text(text = "This is a Compose inside Fragment")
        }
    }


}