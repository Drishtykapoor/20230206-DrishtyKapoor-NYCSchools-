package com.jpmc.a20230130drishtykapoornycschools.presenter

import com.jpmc.a20230130drishtykapoornycschools.repository.SatScore
import com.jpmc.a20230130drishtykapoornycschools.repository.SatScoreApi
import com.jpmc.a20230130drishtykapoornycschools.view.SchoolDetailViewInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SchoolDetailPresenterImpl @Inject constructor(
    private val satScoreApi: SatScoreApi,
    private val schoolDetailViewInterface: SchoolDetailViewInterface
) : SchoolDetailPresenter {

    private val disposable = CompositeDisposable()
    override fun getData(dbn: String) {
        disposable.add(
            satScoreApi.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Success(dbn), this::onError)
        )
    }

    inner class Success(private val dbn: String) : Consumer<SatScore> {
        override fun accept(satScore: SatScore) {
            val filteredData = satScore.filter { it.dbn == dbn }
            if(filteredData.isNotEmpty()){
                val item = filteredData[0]
                schoolDetailViewInterface.setData(item)
            }
            else{
                schoolDetailViewInterface.setFailure("No records found")
            }
        }
    }

    fun onError(t: Throwable) {
        schoolDetailViewInterface.setFailure(t.message.toString())
    }

    override fun dispose() {
        if (!disposable.isDisposed)
            disposable.dispose()
    }
}