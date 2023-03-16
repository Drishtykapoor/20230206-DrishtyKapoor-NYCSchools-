package com.jpmc.a20230130drishtykapoornycschools.di

import androidx.lifecycle.ViewModelProvider
import com.jpmc.a20230130drishtykapoornycschools.presenter.HomeViewModel
import com.jpmc.a20230130drishtykapoornycschools.presenter.HomeViewModelFactory
import com.jpmc.a20230130drishtykapoornycschools.presenter.HomeViewModelImpl
import com.jpmc.a20230130drishtykapoornycschools.repository.HomeRepository
import com.jpmc.a20230130drishtykapoornycschools.repository.HomeRepositoryImpl
import com.jpmc.a20230130drishtykapoornycschools.view.HomeFragment
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [HomeFragmentDependenciesModule.HomeFragViewModelModule::class])
interface HomeFragmentDependenciesModule {

    @Binds
    fun provideHomeRepository(homeRepositoryImpl: HomeRepositoryImpl): HomeRepository

    @Binds
    fun provideHomeViewModel(homeViewModelImpl: HomeViewModelImpl): HomeViewModel

    @Module
    object HomeFragViewModelModule {

        @Provides
        fun provideHomeViewModelFactory(homeRepository: HomeRepository): HomeViewModelFactory {
            return HomeViewModelFactory(homeRepository)
        }

        @Provides
        fun provideViewModel(
            homeFragment: HomeFragment,
            viewModelFactory: HomeViewModelFactory
        ): HomeViewModelImpl {
            val v = ViewModelProvider(
                homeFragment,
                viewModelFactory
            )
            return v.get(HomeViewModelImpl::class.java)
        }
    }
}

