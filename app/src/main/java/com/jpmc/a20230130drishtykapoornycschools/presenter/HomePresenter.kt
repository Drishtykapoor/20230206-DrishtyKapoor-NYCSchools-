package com.jpmc.a20230130drishtykapoornycschools.presenter

import com.jpmc.a20230130drishtykapoornycschools.repository.School
import io.reactivex.Single

interface HomePresenter {
    fun getData()
    fun setData(responseBody: List<School>)
    fun handleError(t: Throwable)
    fun dispose()
}
