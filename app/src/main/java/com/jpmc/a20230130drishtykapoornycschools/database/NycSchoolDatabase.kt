package com.jpmc.a20230130drishtykapoornycschools.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SchoolTable::class], version = 1)
abstract class NycSchoolDatabase : RoomDatabase() {
    abstract fun homeDao(): HomeDao

    companion object {
        private var instance: NycSchoolDatabase? = null

        @Synchronized
        fun getInstance(ctx: Context): NycSchoolDatabase {
            if (instance == null)
                instance = Room.databaseBuilder(
                    ctx.applicationContext,
                    NycSchoolDatabase::class.java,
                    "nyc_school_database.db"
                )
                    .build()

            return instance!!
        }

    }
}