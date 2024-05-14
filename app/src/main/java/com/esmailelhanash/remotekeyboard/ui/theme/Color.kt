package com.esmailelhanash.remotekeyboard.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val Champagne = Color(0xFFEEE0CB)
val Khaki = Color(0xFFBAA898)
val BattleshipGray = Color(0xFF848586)
val OldRose = Color(0xFFC2847A)
val BlackBean = Color(0xFF280003)
// Additional colors definition
val DarkChampagne = Color(0xFFBA8878) // Consider naming it based on your preference
val PinkBlush = Color(0xFFE8B2B5) // Same here, feel free to pick your color name
val DarkSlateGray = Color(0xFF6D6E70) // Choose a name that reflects usage or mood
val LightSlateGray = Color(0xFFA9A9A9) // Customize the name accordingly
val Cream = Color(0xFFFFFBF8)
val DarkRedText = Color(0xFF5D1A1D) // Text color on light primary
val GrayText = Color(0xFF1F1F1F) // Text color on light secondary and tertiary
val LightGraySurface = Color(0xFF424242) // Surface on dark theme
val DarkTextOnBackground = Color(0xFF3B2C2A) // Text on dark tertiary

// a display for the colors with their names:

@Preview
@Composable
fun ColorsList() {
    Column(
        modifier = Modifier.background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row {
            Text(text = "OldRose")
            Box(modifier = Modifier.background(OldRose).size(40.dp))
        }
        Row {
            Text(text = "BlackBean")
            Box(modifier = Modifier.background(BlackBean).size(40.dp))
        }
        Row {
            Text(text = "Champagne")
            Box(modifier = Modifier.background(Champagne).size(40.dp))
        }
        Row {
            Text(text = "Khaki")
            Box(modifier = Modifier.background(Khaki).size(40.dp))
        }
        Row {
            Text(text = "BattleshipGray")
            Box(modifier = Modifier.background(BattleshipGray).size(40.dp))
        }
        Row {
            Text(text = "DarkChampagne")
            Box(modifier = Modifier.background(DarkChampagne).size(40.dp))
        }
        Row {
            Text(text = "PinkBlush")
            Box(modifier = Modifier.background(PinkBlush).size(40.dp))
        }
        Row {
            Text(text = "DarkSlateGray")
            Box(modifier = Modifier.background(DarkSlateGray).size(40.dp))
        }
        Row {
            Text(text = "LightSlateGray")
            Box(modifier = Modifier.background(LightSlateGray).size(40.dp))
        }
        Row {
            Text(text = "Cream")
            Box(modifier = Modifier.background(Cream).size(40.dp))
        }
        Row {
            Text(text = "DarkRedText")
            Box(modifier = Modifier.background(DarkRedText).size(40.dp))
        }
        Row {
            Text(text = "GrayText")
            Box(modifier = Modifier.background(GrayText).size(40.dp))
        }
        Row {
            Text(text = "LightGraySurface")
            Box(modifier = Modifier.background(LightGraySurface).size(40.dp))
        }
        Row {
            Text(text = "DarkTextOnBackground")
            Box(modifier = Modifier.background(DarkTextOnBackground).size(40.dp))
        }
    }
}