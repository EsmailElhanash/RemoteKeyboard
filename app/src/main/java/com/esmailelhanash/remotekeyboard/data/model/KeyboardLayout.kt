package com.esmailelhanash.remotekeyboard.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "keyboard_layout")
data class KeyboardLayout(
    @PrimaryKey(autoGenerate = true)  val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "layout_buttons") val keyboardButtons: List<KeyboardButton>,
    @ColumnInfo(name = "background") val background: LayoutBackground?,
    @ColumnInfo(name = "font") val font: String?,
)
