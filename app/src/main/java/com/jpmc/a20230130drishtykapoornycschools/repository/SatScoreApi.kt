package com.jpmc.a20230130drishtykapoornycschools.repository

import io.reactivex.Observable
import retrofit2.http.GET

interface SatScoreApi {
    @GET("resource/f9bf-2cp4.json")
    fun getData(): Observable<SatScore>
}