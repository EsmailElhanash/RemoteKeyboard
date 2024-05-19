package com.esmailelhanash.remotekeyboard.ui.allLayoutsScreen
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.esmailelhanash.remotekeyboard.R
import com.esmailelhanash.remotekeyboard.data.model.KeyboardLayout
import com.esmailelhanash.remotekeyboard.ui.KeyboardLayoutScreen
import com.esmailelhanash.remotekeyboard.utils.defaultFont
import com.esmailelhanash.remotekeyboard.utils.toFontFamily

@Composable
fun KeyboardLayoutsGrid(
    keyboardLayouts: List<KeyboardLayout>,
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onItemClick: (KeyboardLayout, editMode : Boolean) -> Unit
) {
    // Define the number of columns for the grid
    val columns = GridCells.Fixed(3)

    LazyVerticalGrid(

        columns = columns,
        modifier = modifier,
        content = {
            items(keyboardLayouts) { layout ->
                KeyboardLayoutItem(layout,navController,onItemClick)
            }
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun KeyboardLayoutItem(
    layout: KeyboardLayout, navController: NavHostController,
    onItemClick: (KeyboardLayout, editMode: Boolean) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(8.dp)
            .combinedClickable (
                onClick = {
                        onItemClick(layout,false)
                        navController.navigate(KeyboardLayoutScreen)
                },
                onLongClick = {
                    onItemClick(layout,true)
                    navController.navigate(KeyboardLayoutScreen)
                }
            )
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.layout_icon),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = layout.name,
            style = TextStyle(
                fontFamily = layout.font.toFontFamily ?: defaultFont.value,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            )
        )
    }
}