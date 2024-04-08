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
    suspend fun getAllLayouts(): List<KeyboardLayout>

    @Query("SELECT * FROM keyboard_layout WHERE id = :id")
    suspend fun getLayout(id: Int): KeyboardLayout?

    @Insert
    suspend fun insertLayout(layout: KeyboardLayout)

    @Update
    suspend fun updateLayout(layout: KeyboardLayout)

    @Delete
    suspend fun deleteLayout(layout: KeyboardLayout)
}