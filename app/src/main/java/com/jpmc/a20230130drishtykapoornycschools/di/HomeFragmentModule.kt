package com.jpmc.a20230130drishtykapoornycschools.di

import com.jpmc.a20230130drishtykapoornycschools.view.HomeFragment
import com.jpmc.a20230130drishtykapoornycschools.view.HomeFragmentViewInterface
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeFragmentModule {

    @ContributesAndroidInjector(
        modules = [
            HomeFragmentDependenciesModule::class,
            bindings::class
        ]
    )
    abstract fun provideHomeFragment(): HomeFragment

    @Module
    interface bindings {
        @Binds
        fun bindFragment(impl: HomeFragment): HomeFragmentViewInterface
    }
}