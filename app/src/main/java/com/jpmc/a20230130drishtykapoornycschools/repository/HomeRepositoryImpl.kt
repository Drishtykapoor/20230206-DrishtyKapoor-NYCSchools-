package com.jpmc.a20230130drishtykapoornycschools.repository

import com.jpmc.a20230130drishtykapoornycschools.database.NycSchoolDatabase
import com.jpmc.a20230130drishtykapoornycschools.database.SchoolRow
import com.jpmc.a20230130drishtykapoornycschools.presenter.HomePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val nycSchoolDataApi: NycSchoolDataApi,
    private val nycSchoolDatabase: NycSchoolDatabase
) : HomeRepository {

    private val disposable = CompositeDisposable()
    override fun getData(homePresenter: HomePresenter) {
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
                .subscribe(homePresenter::setData, homePresenter::handleError)
        )
    }


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
