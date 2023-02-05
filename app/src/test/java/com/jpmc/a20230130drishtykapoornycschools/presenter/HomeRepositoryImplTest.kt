package com.jpmc.a20230130drishtykapoornycschools.presenter

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.jpmc.a20230130drishtykapoornycschools.repository.HomeRepository
import com.jpmc.a20230130drishtykapoornycschools.repository.HomeRepositoryImpl
import com.jpmc.a20230130drishtykapoornycschools.repository.NycSchoolDataApi
import com.jpmc.a20230130drishtykapoornycschools.repository.School
import com.jpmc.a20230130drishtykapoornycschools.view.HomeFragmentViewInterface
import io.reactivex.Single
import org.junit.Before
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class HomeRepositoryImplTest {
    @Rule
    @JvmField
    var rule = InstantTaskExecutorRule()

    // Test rule for making the RxJava to run synchronously in unit test
    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }
    @Mock
    lateinit var homePresenter: HomePresenter

    @Mock
    lateinit var nycSchoolDataApi: NycSchoolDataApi

    private lateinit var underTest: HomeRepositoryImpl

    private val response = listOf(School(school_name = "some-name"))

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Mockito.`when`(nycSchoolDataApi.getData())
            .thenReturn(Single.just(response))
        underTest = HomeRepositoryImpl(nycSchoolDataApi)
    }

    @Test
    fun whenGetDataThenCallViewWithData() {
        underTest.getData(homePresenter)
        Mockito.verify(homePresenter).setData(response)
    }

}