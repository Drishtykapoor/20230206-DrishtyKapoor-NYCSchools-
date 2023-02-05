package com.jpmc.a20230130drishtykapoornycschools.view

import com.jpmc.a20230130drishtykapoornycschools.repository.School


interface HomeFragmentViewInterface {
    fun setError(data: String)
    fun setData(responseBody: List<School>)
}
