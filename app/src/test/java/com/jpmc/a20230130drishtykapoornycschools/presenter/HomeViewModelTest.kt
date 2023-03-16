package com.jpmc.a20230130drishtykapoornycschools.presenter

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.jpmc.a20230130drishtykapoornycschools.repository.HomeRepository
import com.jpmc.a20230130drishtykapoornycschools.repository.School
import com.jpmc.a20230130drishtykapoornycschools.view.HomeFragmentViewInterface
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
class HomeViewModelTest {

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
    lateinit var homeRepository: HomeRepository

    @Mock
    lateinit var homeView: HomeFragmentViewInterface

    private lateinit var underTest: HomeViewModelImpl

    private val response = listOf(School(school_name = "some-name"))

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
       // underTest = HomeViewModelImpl(homeRepository, homeView)

    }

    @Test
    fun whenSetDataThenCallViewWithData() {
        //underTest.setData(response)
        Mockito.verify(homeView).setData(response)
    }

    @Test
    fun whenGetDataThenGetDataIsCalledOnRepo() {
        underTest.getData()
        //Mockito.verify(homeRepository).getData(underTest)
    }

}