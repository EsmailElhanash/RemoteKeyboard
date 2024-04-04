package com.esmailelhanash.remotekeyboard.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.esmailelhanash.remotekeyboard.data.dao.KeyboardLayoutDao
import com.esmailelhanash.remotekeyboard.data.model.KeyboardLayout


@Database(entities = [KeyboardLayout::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun keyboardLayoutDao(): KeyboardLayoutDao
}