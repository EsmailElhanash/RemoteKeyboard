package com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.composables

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.KeyboardLayoutViewModel

// composable function for the root view of the keyboard layout fragment

@Composable
fun KeyboardLayoutRoot(navController: NavHostController) {
    val viewModel: KeyboardLayoutViewModel = viewModel()


}