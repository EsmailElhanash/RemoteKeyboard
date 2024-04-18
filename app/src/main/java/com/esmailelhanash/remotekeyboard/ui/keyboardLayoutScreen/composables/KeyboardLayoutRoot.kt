package com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.esmailelhanash.remotekeyboard.ui.LayoutsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KeyboardLayoutRoot(navController: NavHostController, viewModel: LayoutsViewModel) {
    val selectedLayout by viewModel.selectedLayout.observeAsState()
    Scaffold(
        // Use MaterialTheme.colorScheme.background as the default background color
        modifier = Modifier
            .fillMaxSize()
            .background(color = selectedLayout?.background?.color ?: MaterialTheme.colorScheme.background),
        topBar = {
            TopAppBar(
                title = { Text(selectedLayout?.name ?: "Layout Name") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
            )
        },
        content = { innerPadding ->
            Box(
                modifier = Modifier.padding(innerPadding)
            )
        }
    )
}