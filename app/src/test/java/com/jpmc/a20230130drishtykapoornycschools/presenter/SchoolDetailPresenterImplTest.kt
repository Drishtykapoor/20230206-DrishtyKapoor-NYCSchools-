package com.jpmc.a20230130drishtykapoornycschools.presenter

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.jpmc.a20230130drishtykapoornycschools.repository.SatScore
import com.jpmc.a20230130drishtykapoornycschools.repository.SatScoreApi
import com.jpmc.a20230130drishtykapoornycschools.repository.SatScoreItem
import com.jpmc.a20230130drishtykapoornycschools.view.SchoolDetailViewInterface
import io.reactivex.Observable
import org.junit.Before
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class SchoolDetailPresenterImplTest {

    @Rule
    @JvmField
    var rule = InstantTaskExecutorRule()

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    @Mock
    lateinit var satScoreApi: SatScoreApi

    @Mock
    lateinit var schoolDetailView: SchoolDetailViewInterface

    private lateinit var underTest: SchoolDetailPresenterImpl

    private val response = SatScore()
    private val expectedItem = SatScoreItem("1")

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        response.addAll(listOf(expectedItem, SatScoreItem("2"), SatScoreItem("3")))
        Mockito.`when`(satScoreApi.getData())
            .thenReturn(Observable.just(response))
        underTest = SchoolDetailPresenterImpl(satScoreApi, schoolDetailView)

    }

    @Test
    fun whenGetDataThenCallViewWithData() {
        underTest.getData("1")
        Mockito.verify(schoolDetailView).setData(expectedItem)
    }

    @Test
    fun whenNoDataThenThrowError() {
        underTest.getData("4")
        Mockito.verify(schoolDetailView).setFailure("No records found")
    }
}