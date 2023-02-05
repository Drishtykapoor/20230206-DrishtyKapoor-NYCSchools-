package com.jpmc.a20230130drishtykapoornycschools.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.jpmc.a20230130drishtykapoornycschools.databinding.SchoolDetailFragmentBinding
import com.jpmc.a20230130drishtykapoornycschools.presenter.SchoolDetailPresenter
import com.jpmc.a20230130drishtykapoornycschools.repository.SatScoreItem
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class SchoolDetailFragment : DaggerFragment(), SchoolDetailViewInterface {

    private lateinit var binding: SchoolDetailFragmentBinding

    private val args: SchoolDetailFragmentArgs by navArgs()

    @Inject
    lateinit var presenter: SchoolDetailPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SchoolDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onResume() {
        super.onResume()
        // just some additional random school image to make the app look nicer
        Picasso.with(context).isLoggingEnabled = true
        Picasso.with(context).load(listOfRandomSchoolImages[0]).into(binding.imageView)
        presenter.getData(args.schoolData.dbn)
    }

    private val listOfRandomSchoolImages =
        listOf("https://thumbs.dreamstime.com/b/bronx-ny-usa-new-york-city-public-school-building-beneath-cloud-filled-sky-171346134.jpg")

    override fun setData(item: SatScoreItem) {
        binding.title.text = item.school_name
        binding.criticalMath.text = item.sat_math_avg_score
        binding.criticalReading.text = item.sat_critical_reading_avg_score
        binding.criticalWriting.text = item.sat_writing_avg_score
    }

    override fun setFailure(message: String?) {
        binding.nameRow.visibility = View.GONE
        binding.criticalMathRow.visibility = View.GONE
        binding.criticalWritingRow.visibility = View.GONE
        binding.totalStudentRow.visibility = View.GONE
        binding.imageView.visibility = View.GONE
        binding.error.visibility = View.VISIBLE
        binding.error.text = message
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dispose()
    }
}