package com.jpmc.a20230130drishtykapoornycschools.presenter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.jpmc.a20230130drishtykapoornycschools.database.HomeDao
import com.jpmc.a20230130drishtykapoornycschools.database.NycSchoolDatabase
import com.jpmc.a20230130drishtykapoornycschools.repository.HomeRepositoryImpl
import com.jpmc.a20230130drishtykapoornycschools.repository.NycSchoolDataApi
import com.jpmc.a20230130drishtykapoornycschools.repository.School
import io.reactivex.Single
import org.junit.Assert.assertEquals
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
    lateinit var nycSchoolDataApi: NycSchoolDataApi

    @Mock
    lateinit var database: NycSchoolDatabase

    @Mock
    lateinit var homeDao: HomeDao

    var schoolData = MutableLiveData<List<School>>()
    var errorData = MutableLiveData<String>()

    private lateinit var underTest: HomeRepositoryImpl

    private val expectedResponse = listOf(School(school_name = "some-name"))

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Mockito.`when`(database.homeDao()).thenReturn(homeDao)
        Mockito.`when`(nycSchoolDataApi.getData())
            .thenReturn(Single.just(expectedResponse))
        underTest = HomeRepositoryImpl(nycSchoolDataApi,database)
    }

    @Test
    fun whenGetDataThenCallViewWithData() {
        val myObserver = MyObserver()
        schoolData.observeForever(myObserver)
        underTest.getData(schoolData, errorData)
        assertEquals(expectedResponse, myObserver.actualResponse)
        schoolData.removeObserver(myObserver)
    }

    class MyObserver : Observer<List<School>> {
        var actualResponse : List<School>? = null
        override fun onChanged(t: List<School>?) {
            actualResponse = t
        }
    }
}