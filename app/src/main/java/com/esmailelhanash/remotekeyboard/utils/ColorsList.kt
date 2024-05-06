package com.esmailelhanash.remotekeyboard.utils

import androidx.compose.ui.graphics.Color


private fun generateDistinctColors(numColors: Int): List<Color> {
    val colors = mutableListOf<Color>()
    // Adjust the step values based on the number of colors to generate a wide range of colors
    val hueStep = 360 / (numColors / 3) // Divide by 3 to distribute hues over three lightness levels
    val lightnessLevels = listOf(30, 60, 90) // Example lightness levels for dark, medium, and light colors

    for (lightness in lightnessLevels) {
        for (i in 0 until numColors / 3) { // Divide total number by 3 because we're iterating over three lightness levels
            val hue = i * hueStep
            // Adjust saturation for each hue to add variety
            val saturation = when {
                i % 3 == 0 -> 70 // Some arbitrary adjustments to add variety
                i % 3 == 1 -> 80
                else -> 90
            }
            colors.add(hslToColor(hue, saturation, lightness))
        }
    }

    // Adding variations from white to black by adjusting lightness
    // Adjusting the grayscale generation to include pure white and avoid repeating pure black
    val numberOfGrayscaleColors = numColors / 3
    for (i in 0 until numberOfGrayscaleColors) {
        // Adjusting the calculation of lightness to ensure the range from 100 to 0 is covered
        val lightness = 100 - (i * 100 / (numberOfGrayscaleColors - 1))
        colors.add(hslToColor(0, 0, lightness))
    }

    return colors
}

private fun hslToColor(hue: Int, saturation: Int, lightness: Int): Color {
    val s = saturation / 100f
    val l = lightness / 100f
    val c = (1f - kotlin.math.abs(2 * l - 1f)) * s
    val x = c * (1f - kotlin.math.abs((hue / 60f % 2f) - 1f))
    val m = l - c / 2f
    val tempValues = when (hue) {
        in 0 until 60 -> arrayOf(c, x, 0f)
        in 60 until 120 -> arrayOf(x, c, 0f)
        in 120 until 180 -> arrayOf(0f, c, x)
        in 180 until 240 -> arrayOf(0f, x, c)
        in 240 until 300 -> arrayOf(x, 0f, c)
        in 300 until 360 -> arrayOf(c, 0f, x)
        else -> arrayOf(0f, 0f, 0f)
    }
    return Color(
        red = ((tempValues[0] + m) * 255).toInt(),
        green = ((tempValues[1] + m) * 255).toInt(),
        blue = ((tempValues[2] + m) * 255).toInt()
    )
}

// Usage
val colorsList = generateDistinctColors(200)