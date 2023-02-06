package com.jpmc.a20230130drishtykapoornycschools.di

import android.content.Context
import com.jpmc.a20230130drishtykapoornycschools.database.NycSchoolDatabase
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @Provides
    fun provideSchoolDB(context: Context): NycSchoolDatabase {
        return NycSchoolDatabase.getInstance(context)
    }
}