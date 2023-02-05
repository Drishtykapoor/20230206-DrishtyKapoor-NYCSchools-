package com.jpmc.a20230130drishtykapoornycschools.repository

import com.jpmc.a20230130drishtykapoornycschools.presenter.HomePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val nycSchoolDataApi: NycSchoolDataApi
) : HomeRepository {

    private val disposable = CompositeDisposable()
    override fun getData(homePresenter: HomePresenter) {
        disposable.add(
            nycSchoolDataApi.getData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(homePresenter::setData, homePresenter::handleError)
        )
    }

    override fun dispose() {
        if (!disposable.isDisposed)
            disposable.dispose()
    }

}