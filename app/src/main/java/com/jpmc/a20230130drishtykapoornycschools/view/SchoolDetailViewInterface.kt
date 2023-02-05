package com.jpmc.a20230130drishtykapoornycschools.view

import com.jpmc.a20230130drishtykapoornycschools.repository.SatScoreItem

interface SchoolDetailViewInterface {
    fun setData(item : SatScoreItem)
    fun setFailure(message: String?)
}
