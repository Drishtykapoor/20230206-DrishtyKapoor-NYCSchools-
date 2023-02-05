package com.jpmc.a20230130drishtykapoornycschools.di

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.jpmc.a20230130drishtykapoornycschools.repository.NycSchoolDataApi
import com.jpmc.a20230130drishtykapoornycschools.repository.SatScoreApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
object ApiModule {

    @Provides
    fun provideSchoolInfoApi(retrofit: Retrofit): NycSchoolDataApi {
        return retrofit.create(NycSchoolDataApi::class.java)
    }

    @Provides
    fun provideSatScoreApi(retrofit: Retrofit): SatScoreApi {
        return retrofit.create(SatScoreApi::class.java)
    }

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("https://data.cityofnewyork.us/")
            .build()
    }
}