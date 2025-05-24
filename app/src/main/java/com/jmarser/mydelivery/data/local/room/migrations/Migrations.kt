package com.jmarser.mydelivery.data.local.room.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * Project: My Delivery
 * File: Migrations
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 23/05/2025
 */

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(
            """CREATE TABLE IF NOT EXISTS dishes_favorites(
        id TEXT NOT NULL PRIMARY KEY,
        name TEXT,
        description TEXT,
        price REAL,
        imageUrl TEXT
        )""".trimIndent()
        )
    }
}