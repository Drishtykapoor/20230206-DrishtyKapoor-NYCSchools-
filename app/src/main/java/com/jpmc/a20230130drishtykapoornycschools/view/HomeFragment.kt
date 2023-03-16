package com.jpmc.a20230130drishtykapoornycschools.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.LinearLayoutManager
import com.jpmc.a20230130drishtykapoornycschools.R
import com.jpmc.a20230130drishtykapoornycschools.adapter.SchoolListAdapter
import com.jpmc.a20230130drishtykapoornycschools.adapter.VerticalMarginDecorator
import com.jpmc.a20230130drishtykapoornycschools.databinding.HomeFragmentBinding
import com.jpmc.a20230130drishtykapoornycschools.presenter.HomeViewModelImpl
import com.jpmc.a20230130drishtykapoornycschools.presenter.SortOrder
import com.jpmc.a20230130drishtykapoornycschools.repository.School
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class HomeFragment : DaggerFragment(), HomeFragmentViewInterface,
    PopupMenu.OnMenuItemClickListener {

    private lateinit var binding: HomeFragmentBinding

    @Inject
    lateinit var viewModel: HomeViewModelImpl

    @Inject
    lateinit var adapter: SchoolListAdapter
    //fieldinjection


    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        binding.recyclerview.layoutManager = LinearLayoutManager(context)
        binding.recyclerview.adapter = adapter
        binding.recyclerview.addItemDecoration(
            VerticalMarginDecorator(resources.getDimensionPixelSize(R.dimen.margin_small))
        )
        binding.sort.setOnClickListener { showPopup(it) }
        return binding.root
    }


    override fun onResume() {
        super.onResume()
        viewModel.schoolList.observe(this) { adapter.setData(it) }
        viewModel.errorData.observe(this) { setError(it) }
        viewModel.getData()
    }

    private fun showPopup(v: View) {
        context?.let {
            val popup = PopupMenu(it, v)
            val inflater: MenuInflater = popup.menuInflater
            inflater.inflate(R.menu.sort_menu, popup.menu)
            popup.setOnMenuItemClickListener(this)
            popup.show()
        }
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.ascending -> {
                adapter.sort(SortOrder.Ascending)
                true
            }
            R.id.descending -> {
                adapter.sort(SortOrder.Descending)
                true
            }
            else -> false
        }
    }

    override fun setError(data: String) {
        Log.d("nycschool", data)
    }

    override fun setData(data: List<School>) {
        adapter.setData(data)
    }
}