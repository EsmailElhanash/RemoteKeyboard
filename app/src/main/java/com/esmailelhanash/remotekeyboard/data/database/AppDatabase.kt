package com.esmailelhanash.remotekeyboard.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.esmailelhanash.remotekeyboard.data.dao.KeyboardLayoutDao
import com.esmailelhanash.remotekeyboard.data.model.KeyboardLayout
import com.esmailelhanash.remotekeyboard.data.typeConverters.KeyboardButtonTypeConverter
import com.esmailelhanash.remotekeyboard.data.typeConverters.LayoutBackgroundTypeConverter


@Database(entities = [KeyboardLayout::class], version = 3, exportSchema = true)
@TypeConverters(KeyboardButtonTypeConverter::class, LayoutBackgroundTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun keyboardLayoutDao(): KeyboardLayoutDao
}