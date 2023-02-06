package com.jpmc.a20230130drishtykapoornycschools.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HomeDao {

    @Insert
    fun insert(school: SchoolRow)

    @Query("DELETE FROM school_table")
    fun purseTable()

    @Query("select * from school_table")
    fun getAllSchools(): List<SchoolRow>

}