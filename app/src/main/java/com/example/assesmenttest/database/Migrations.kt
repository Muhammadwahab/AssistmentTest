package com.example.assesmenttest.database

import android.util.Log
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object Migrations {

    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE User ADD COLUMN referral_code TEXT DEFAULT ''")
            Log.e(Migrations::class.simpleName,"migration 1 to 2")
        }
    }
    val MIGRATION_2_3 = object : Migration(2, 3) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE User ADD COLUMN email TEXT DEFAULT ''")
            Log.e(Migrations::class.simpleName,"migration 2 to 3")
        }
    }
    val MIGRATION_3_4 = object : Migration(3, 4) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE User ADD COLUMN jwt TEXT DEFAULT ''")
            Log.e(Migrations::class.simpleName,"migration 3 to 4")
        }
    }
}