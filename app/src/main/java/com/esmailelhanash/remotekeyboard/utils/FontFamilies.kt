package com.esmailelhanash.remotekeyboard.utils

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.esmailelhanash.remotekeyboard.R

private val BitterRegular = FontFamily(
    Font(R.font.bitter_regular, FontWeight.Normal),
)
private val CabinRegular = FontFamily(
    Font(R.font.cabin_regular, FontWeight.Normal),
)
private val dosis_regular = FontFamily(
    Font(R.font.dosis_regular, FontWeight.Normal),
)
private val fira_sans_regular = FontFamily(
    Font(R.font.fira_sans_regular, FontWeight.Normal),
)
private val karla_regular = FontFamily(
    Font(R.font.karla_regular, FontWeight.Normal),
)
private val lora_regular = FontFamily(
    Font(R.font.lora_regular, FontWeight.Normal),
)
private val merriweather_regular = FontFamily(
    Font(R.font.merriweather_regular, FontWeight.Normal),
)
private val mukta_regular = FontFamily(
    Font(R.font.mukta_regular, FontWeight.Normal),
)
private val open_sans_regular = FontFamily(
    Font(R.font.open_sans_regular, FontWeight.Normal),
)
private val oswald_regular = FontFamily(
    Font(R.font.oswald_regular, FontWeight.Normal),
)
private val poppins_regular = FontFamily(
    Font(R.font.poppins_regular, FontWeight.Normal),
)
private val roboto_regular = FontFamily(
    Font(R.font.roboto_regular, FontWeight.Normal),
)
private val ubuntu_sans_regular = FontFamily(
    Font(R.font.ubuntu_sans_regular, FontWeight.Normal),
)
private val zilla_slab_regular = FontFamily(
    Font(R.font.zilla_slab_regular, FontWeight.Normal),
)


// a map that hold all the fonts mapping name to font family:
val fontsMap = mapOf(
    "Bitter Regular" to BitterRegular,
    "Cabin Regular" to CabinRegular,
    "Dosis Regular" to dosis_regular,
    "Fira Sans Regular" to fira_sans_regular,
    "Karla Regular" to karla_regular,
    "Lora Regular" to lora_regular,
    "Merriweather Regular" to merriweather_regular,
    "Mukta Regular" to mukta_regular,
    "Open Sans Regular" to open_sans_regular,
    "Oswald Regular" to oswald_regular,
    "Poppins Regular" to poppins_regular,
    "Roboto Regular" to roboto_regular,
    "Ubuntu Sans Regular" to ubuntu_sans_regular,
    "Zilla Slab Regular" to zilla_slab_regular,
)

val defaultFont =  fontsMap.entries.first()

val String?.toFontFamily: FontFamily?
    get() = fontsMap[this]