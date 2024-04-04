package com.esmailelhanash.remotekeyboard.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class KeyboardLayout(
    @PrimaryKey(autoGenerate = true)  val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "layout_buttons") val keyboardButtons: List<KeyboardButton>,
    @ColumnInfo(name = "background_img") val background: String?,
)
