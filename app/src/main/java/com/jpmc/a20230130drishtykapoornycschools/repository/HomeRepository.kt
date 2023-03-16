package com.jpmc.a20230130drishtykapoornycschools.repository

import androidx.lifecycle.MutableLiveData

interface HomeRepository {
    fun getData(liveData: MutableLiveData<List<School>>, errorLiveData: MutableLiveData<String>)
    fun dispose()
}