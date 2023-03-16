package com.jpmc.a20230130drishtykapoornycschools.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jpmc.a20230130drishtykapoornycschools.repository.HomeRepository

class HomeViewModelFactory(private val homeRepo: HomeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModelImpl(homeRepo) as T
    }
}