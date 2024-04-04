package com.esmailelhanash.remotekeyboard.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.esmailelhanash.remotekeyboard.data.model.KeyboardLayout

@Dao
interface KeyboardLayoutDao {
    @Query("SELECT * FROM keyboard_layout")
    fun getAllLayouts(): List<KeyboardLayout>

    @Query("SELECT * FROM keyboard_layout WHERE id = :id")
    fun getLayout(id: Int): KeyboardLayout?

    @Insert
    fun insertLayout(layout: KeyboardLayout)

    @Update
    fun updateLayout(layout: KeyboardLayout)

    @Delete
    fun deleteLayout(layout: KeyboardLayout)
}