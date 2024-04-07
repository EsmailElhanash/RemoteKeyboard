package com.esmailelhanash.remotekeyboard.data.database

import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
object DatabaseClient {
    private const val DBNAME = "keyboard-layouts-database"
    @Volatile
    private var INSTANCE: AppDatabase? = null

    suspend fun getDatabase(context: Context): AppDatabase = withContext(Dispatchers.IO) {
        val tempInstance = INSTANCE
        if (tempInstance != null) {
            return@withContext tempInstance
        }
        synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                DBNAME
            )
                .fallbackToDestructiveMigration()
                .build()
            INSTANCE = instance
            return@withContext instance
        }
    }
}
