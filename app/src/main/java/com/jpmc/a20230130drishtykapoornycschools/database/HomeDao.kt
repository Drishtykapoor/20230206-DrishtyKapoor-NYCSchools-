package com.jpmc.a20230130drishtykapoornycschools.database

import androidx.room.*

@Dao
interface HomeDao {

    @Query("select * from school_table")
    fun getAllSchools(): List<SchoolTable>

}