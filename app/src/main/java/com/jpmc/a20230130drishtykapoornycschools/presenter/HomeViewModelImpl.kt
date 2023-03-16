package com.jpmc.a20230130drishtykapoornycschools.presenter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jpmc.a20230130drishtykapoornycschools.repository.HomeRepository
import com.jpmc.a20230130drishtykapoornycschools.repository.School
import javax.inject.Inject

class HomeViewModelImpl @Inject constructor(
    private val homeRepository: HomeRepository,
) : ViewModel(), HomeViewModel {

    val schoolList = MutableLiveData<List<School>>()
    val errorData = MutableLiveData<String>()

    override fun getData() {
        homeRepository.getData(schoolList, errorData)
    }

    override fun dispose() {
        homeRepository.dispose()
    }

}