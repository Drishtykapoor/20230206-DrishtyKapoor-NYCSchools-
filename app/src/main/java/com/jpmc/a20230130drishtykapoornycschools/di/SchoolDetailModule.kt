package com.jpmc.a20230130drishtykapoornycschools.di

import com.jpmc.a20230130drishtykapoornycschools.view.SchoolDetailFragment
import com.jpmc.a20230130drishtykapoornycschools.view.SchoolDetailViewInterface
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SchoolDetailModule {

    @ContributesAndroidInjector(
        modules = [
            SchoolDetailFragmentDependenciesModule::class,
            bindings::class
        ]
    )
    abstract fun provideSchoolDetailFragment(): SchoolDetailFragment


    @Module
    interface bindings {
        @Binds
        fun bindFragment(impl: SchoolDetailFragment): SchoolDetailViewInterface
    }
}