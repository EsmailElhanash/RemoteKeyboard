package com.esmailelhanash.remotekeyboard.ui.layoutsactivity.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.esmailelhanash.remotekeyboard.ui.layoutsactivity.KeyboardLayoutsViewModel
import com.esmailelhanash.remotekeyboard.ui.theme.RemoteKeyboardTheme


@Composable
fun Fab(viewModel: KeyboardLayoutsViewModel) {
    var showDialog by remember { mutableStateOf(false) }
    FloatingActionButton(
        onClick = { showDialog = true }, // Set the state to true to show the dialog
        modifier = Modifier.padding(16.dp)

    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = null
        )
    }
    if (showDialog) {
        AddLayoutDialog (viewModel = viewModel){
            showDialog = it
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Root(viewModel: KeyboardLayoutsViewModel) {
    Scaffold(
        floatingActionButton = { Fab(viewModel) },
        // app bar:
        topBar = {
            TopAppBar(
                title = { Text("Keyboard Layouts") },
                navigationIcon = {
                    IconButton(onClick = { /* Handle navigation icon press */ }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    // Add actions here if needed, for example a search icon
                    IconButton(onClick = { /* Handle action icon press */ }) {
                        Icon(Icons.Filled.Search, contentDescription = "Search")
                    }
                }
            )
        },
        content = { innerPadding ->
            KeyboardLayoutsGrid(
                modifier = Modifier.padding(innerPadding),
                keyboardLayouts = viewModel.layoutsLiveData.observeAsState().value ?: listOf()
            )
        }
    )
}




@Preview(showBackground = true)
@Composable
fun RootPreview() {
    RemoteKeyboardTheme {
//        Root()
    }
}