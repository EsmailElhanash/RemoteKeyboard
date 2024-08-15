package com.esmailelhanash.remotekeyboard.ui.allLayoutsScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.esmailelhanash.remotekeyboard.R
import com.esmailelhanash.remotekeyboard.data.model.KeyboardLayout
import com.esmailelhanash.remotekeyboard.ui.LayoutsViewModel


@Composable
private fun Fab(viewModel: LayoutsViewModel) {
    var showDialog by remember { mutableStateOf(false) }
    FloatingActionButton(
        onClick = { showDialog = true }, // Set the state to true to show the dialog
        modifier = Modifier.padding(
            start = 48.dp,
            end = 48.dp,
            bottom = 16.dp,
            top = 16.dp
        )
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
fun AllLayoutsRoot(navController: NavHostController,viewModel: LayoutsViewModel) {

    Scaffold(
        floatingActionButton = { Fab(viewModel) },
        // app bar:
        topBar = {
            TopAppBar(
                title = { Text("Keyboard Layouts") },
                colors = TopAppBarColors(
                    containerColor = Color(0xff5c4743),
                    titleContentColor = Color(0xffd3c7b9),
                    actionIconContentColor = Color.White,
                    scrolledContainerColor = Color.White,
                    navigationIconContentColor = Color.White,
                ),

                actions = {
                    // Add your actions here
                    // display a small icon from drawables

                    Image(
                        painter = painterResource(R.drawable.vb),
                        contentDescription = null
                    )
                }

            )
        },
        content = { innerPadding ->
            KeyboardLayoutsGrid(
                modifier = Modifier.padding(innerPadding),
                keyboardLayouts = viewModel.layoutsLiveData.observeAsState().value ?: listOf(),
                navController = navController
            ){ keyboardLayout: KeyboardLayout, b: Boolean ->
                viewModel.selectLayout(keyboardLayout)
                viewModel.setEditMode(b)
            }
        }
    )
}