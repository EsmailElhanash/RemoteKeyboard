package com.esmailelhanash.remotekeyboard.ui.layoutsactivity.composables
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.esmailelhanash.remotekeyboard.data.model.KeyboardLayout

@Composable
fun KeyboardLayoutsGrid(keyboardLayouts: List<KeyboardLayout>) {
    // Define the number of columns for the grid
    val columns = GridCells.Fixed(3)

    LazyVerticalGrid(
        columns = columns,
        contentPadding = PaddingValues(8.dp),
        content = {
            items(keyboardLayouts) { layout ->
                KeyboardLayoutItem(layout)
            }
        }
    )
}

@Composable
private fun KeyboardLayoutItem(layout: KeyboardLayout) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(8.dp)
    ) {

    }
}