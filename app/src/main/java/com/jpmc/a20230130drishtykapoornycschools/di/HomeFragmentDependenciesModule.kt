package com.jpmc.a20230130drishtykapoornycschools.di

import com.jpmc.a20230130drishtykapoornycschools.presenter.HomePresenter
import com.jpmc.a20230130drishtykapoornycschools.presenter.HomePresenterImpl
import com.jpmc.a20230130drishtykapoornycschools.repository.HomeRepository
import com.jpmc.a20230130drishtykapoornycschools.repository.HomeRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface HomeFragmentDependenciesModule {

    @Binds
    fun provideHomePresenter(homePresenterImpl: HomePresenterImpl): HomePresenter

    @Binds
    fun provideHomeRepository(homeRepositoryImpl: HomeRepositoryImpl): HomeRepository
}
