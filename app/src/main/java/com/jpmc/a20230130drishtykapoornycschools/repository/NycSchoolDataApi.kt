package com.jpmc.a20230130drishtykapoornycschools.repository

import io.reactivex.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface NycSchoolDataApi {
    @GET("resource/s3k6-pzi2.json")
    fun getData(): Single<List<School>>

    @GET("resource/s3k6-pzi2.json")
    suspend fun getSchoolData(): Response<List<School>>
}