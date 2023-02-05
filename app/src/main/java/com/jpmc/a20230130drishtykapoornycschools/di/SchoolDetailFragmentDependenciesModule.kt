package com.jpmc.a20230130drishtykapoornycschools.di

import com.jpmc.a20230130drishtykapoornycschools.presenter.SchoolDetailPresenter
import com.jpmc.a20230130drishtykapoornycschools.presenter.SchoolDetailPresenterImpl
import dagger.Binds
import dagger.Module

@Module
interface SchoolDetailFragmentDependenciesModule {

    @Binds
    fun bindSchoolDetailPresenter(
        schoolDetailPresenterImpl: SchoolDetailPresenterImpl
    ): SchoolDetailPresenter
}
