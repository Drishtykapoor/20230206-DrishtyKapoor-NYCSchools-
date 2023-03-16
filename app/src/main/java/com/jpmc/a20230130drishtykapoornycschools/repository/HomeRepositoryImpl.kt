package com.jpmc.a20230130drishtykapoornycschools.repository

import androidx.lifecycle.MutableLiveData
import com.jpmc.a20230130drishtykapoornycschools.database.NycSchoolDatabase
import com.jpmc.a20230130drishtykapoornycschools.database.SchoolRow
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val nycSchoolDataApi: NycSchoolDataApi,
    private val nycSchoolDatabase: NycSchoolDatabase,
) : HomeRepository {

    private val disposable = CompositeDisposable()
    override fun getData(schoolListLiveData: MutableLiveData<List<School>>,
                         errorLiveData: MutableLiveData<String>
    ) {
        disposable.add(
            nycSchoolDataApi.getData()
                .doOnSuccess {
                    nycSchoolDatabase.homeDao().purseTable()
                    it.forEach { t ->
                        nycSchoolDatabase.homeDao().insert(t.toSchoolRow())
                    }
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ schoolListLiveData.value = it }, { errorLiveData.value = it.message })
        )
    }


//    fun updateDatabase(data: List<School>) {
//        nycSchoolDatabase.homeDao().purseTable()
//        data.forEach { t ->
//            nycSchoolDatabase.homeDao().insert(t.toSchoolRow())
//        }
//    }

//    override fun getData(homePresenter: HomePresenter) {
//        CoroutineScope(Dispatchers.IO).launch {
//            val response = nycSchoolDataApi.getSchoolData()
//            response.body()?.let {
//                updateDatabase(it)
//                withContext(Dispatchers.Main) {
//                    homePresenter.setData(it)
//                }
//            }
//        }
//
//    }


    override fun dispose() {
        if (!disposable.isDisposed)
            disposable.dispose()
    }

}

private fun School.toSchoolRow(): SchoolRow {
    return SchoolRow(
        this.academicopportunities1,
        this.academicopportunities2,
        this.academicopportunities3,
        this.addtl_info1,
        this.attendance_rate,
        this.bbl,
        this.bin,
        this.boro,
        this.borough,
        this.building_code,
        this.bus,
        this.census_tract,
        this.city,
        this.code1,
        this.community_board,
        this.council_district,
        this.dbn,
        this.directions1,
        this.eligibility1,
        this.ell_programs,
        this.extracurricular_activities,
        this.fax_number,
        this.finalgrades,
        this.grade9geapplicants1,
        this.grade9geapplicantsperseat1,
        this.grade9gefilledflag1,
        this.grade9swdapplicants1,
        this.grade9swdapplicantsperseat1,
        this.grade9swdfilledflag1,
        this.grades2018,
        this.interest1,
        this.language_classes,
        this.latitude,
        this.location,
        this.longitude,
        this.method1,
        this.neighborhood,
        this.nta,
        this.overview_paragraph,
        this.pct_stu_enough_variety,
        this.pct_stu_safe,
        this.phone_number,
        this.primary_address_line_1,
        this.program1,
        this.school_10th_seats,
        this.school_email,
        this.school_name,
        this.school_sports,
        this.seats101,
        this.seats9ge1,
        this.seats9swd1,
        this.state_code,
        this.subway,
        this.total_students,
        this.transfer,
        this.website,
        this.zip
    )
}
