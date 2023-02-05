package com.jpmc.a20230130drishtykapoornycschools.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.jpmc.a20230130drishtykapoornycschools.R
import com.jpmc.a20230130drishtykapoornycschools.presenter.SortOrder
import com.jpmc.a20230130drishtykapoornycschools.repository.School
import com.jpmc.a20230130drishtykapoornycschools.view.HomeFragmentDirections
import javax.inject.Inject

class SchoolListAdapter @Inject constructor(val navController: NavController) :
    RecyclerView.Adapter<SchoolListAdapter.ViewHolder>() {

    private val myData = mutableListOf<School>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.school_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return myData.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)

        init {
            name.setOnClickListener {
                navController
                    .navigate(
                        HomeFragmentDirections.detailAction(
                            myData[bindingAdapterPosition]
                        ),
                    )
            }
        }

        fun onBind(position: Int) {
            name.text = myData[position].school_name
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(listClothData: List<School>) {
        myData.clear()
        myData.addAll(listClothData)
        notifyDataSetChanged()
    }

    fun sort(sortOrder: SortOrder) {
        when (sortOrder) {
            SortOrder.Ascending -> myData.sortBy { it.school_name }
            SortOrder.Descending -> myData.sortByDescending { it.school_name }
        }
        notifyDataSetChanged()
    }
}