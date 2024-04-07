package com.esmailelhanash.remotekeyboard.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.esmailelhanash.remotekeyboard.data.dao.KeyboardLayoutDao
import com.esmailelhanash.remotekeyboard.data.model.KeyboardLayout
import com.esmailelhanash.remotekeyboard.data.typeConverters.KeyboardButtonTypeConverter


@Database(entities = [KeyboardLayout::class], version = 1)
@TypeConverters(KeyboardButtonTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun keyboardLayoutDao(): KeyboardLayoutDao
}