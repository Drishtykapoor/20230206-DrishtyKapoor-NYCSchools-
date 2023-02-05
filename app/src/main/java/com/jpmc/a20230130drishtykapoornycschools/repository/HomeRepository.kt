package com.jpmc.a20230130drishtykapoornycschools.repository

import com.jpmc.a20230130drishtykapoornycschools.presenter.HomePresenter

interface HomeRepository {
    fun getData(homePresenter: HomePresenter)
    fun dispose()
}