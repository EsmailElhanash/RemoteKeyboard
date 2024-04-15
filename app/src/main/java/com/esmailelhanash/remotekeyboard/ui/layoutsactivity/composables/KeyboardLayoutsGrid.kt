package com.esmailelhanash.remotekeyboard.ui.layoutsactivity.composables
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.esmailelhanash.remotekeyboard.R
import com.esmailelhanash.remotekeyboard.data.model.KeyboardLayout

@Composable
fun KeyboardLayoutsGrid(keyboardLayouts: List<KeyboardLayout>, modifier: Modifier = Modifier) {
    // Define the number of columns for the grid
    val columns = GridCells.Fixed(3)

    LazyVerticalGrid(

        columns = columns,
        modifier = modifier,
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
        // an icon layout_icon.xml:
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.layout_icon),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = layout.name,
            style = MaterialTheme.typography.bodySmall
        )
    }
}