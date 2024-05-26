package com.esmailelhanash.remotekeyboard.data.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2: Migration = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        // If you're just changing how the data is stored but not the column type (still TEXT),
        // and assuming your TypeConverter handles the conversion, you might not need to change the schema.
        // However, if you're renaming the column or need to ensure the data format is correct, you would:

        // Step 1: Rename the old table
        db.execSQL("ALTER TABLE keyboard_layout RENAME TO keyboard_layout_old")

        // Step 2: Create the new table with the correct column name and types
        db.execSQL("""
            CREATE TABLE keyboard_layout (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                name TEXT NOT NULL,
                layout_buttons TEXT NOT NULL,
                background TEXT
            )
        """.trimIndent())

        // Step 3: Copy the data from the old table to the new table
        // Assuming the background_img data can be directly used by the TypeConverter without modification
        db.execSQL("""
            INSERT INTO keyboard_layout (id, name, layout_buttons, background)
            SELECT id, name, layout_buttons, background_img AS background FROM keyboard_layout_old
        """.trimIndent())

        // Step 4: Drop the old table
        db.execSQL("DROP TABLE keyboard_layout_old")
    }
}

val MIGRATION_2_3: Migration = object : Migration(2, 3) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("ALTER TABLE keyboard_layout ADD COLUMN font TEXT")
    }

}
val MIGRATION_3_4: Migration = object : Migration(3, 4) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("ALTER TABLE keyboard_layout ADD COLUMN shadow INTEGER")
    }
}

