package com.jpmc.a20230130drishtykapoornycschools.presenter

import com.jpmc.a20230130drishtykapoornycschools.repository.HomeRepository
import com.jpmc.a20230130drishtykapoornycschools.repository.School
import com.jpmc.a20230130drishtykapoornycschools.view.HomeFragmentViewInterface
import javax.inject.Inject

class HomePresenterImpl @Inject constructor(
    private val homeRepository: HomeRepository,
    private val homeFragmentViewInterface: HomeFragmentViewInterface
) : HomePresenter {

    override fun getData() {
        homeRepository.getData(this)
    }

    override fun setData(responseBody: List<School>) {
        homeFragmentViewInterface.setData(responseBody)
    }

    override fun handleError(t: Throwable) {
        homeFragmentViewInterface.setError(t.message.toString())
    }

    override fun dispose() {
        homeRepository.dispose()
    }

}