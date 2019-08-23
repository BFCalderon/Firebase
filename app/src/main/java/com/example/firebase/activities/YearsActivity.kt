package com.example.firebase.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase.R
import com.example.firebase.adapters.TreeInformationAdapter
import com.example.firebase.valueobjects.DateInformationVO
import com.example.firebase.viewmodel.TreeInformationViewModel
import kotlinx.android.synthetic.main.activity_years.*
import java.util.*

class YearsActivity : AppCompatActivity(), TreeInformationAdapter.INotifyItemSelected {

    private lateinit var treeInformationViewModel: TreeInformationViewModel
    private var adapterInformationDates: TreeInformationAdapter ?= null
    private var recyclerDateInfBD: RecyclerView?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_years)
        treeInformationViewModel = run {
            ViewModelProviders.of(this).get(TreeInformationViewModel::class.java)
        }
        readYears()
    }

    private fun readYears() {
        treeInformationViewModel.getAllYearInformation()
            .observe(this, androidx.lifecycle.Observer<List<DateInformationVO>> { dateInf ->
                startRecycler(dateInf as ArrayList<DateInformationVO>)
            })
    }

    private fun startRecycler(yearsList: ArrayList<DateInformationVO>){
        adapterInformationDates = TreeInformationAdapter(yearsList)
        recyclerDateInfBD = recyclerYears
        recyclerDateInfBD!!.layoutManager = LinearLayoutManager(this)
        recyclerDateInfBD!!.adapter = this.adapterInformationDates
        adapterInformationDates!!.iNotifyItemSelected = this
    }

    override fun sendChangeCoor(position: String) {
        val intent = Intent(this, MonthsActivity::class.java)
        intent.putExtra("year", position)
        Toast.makeText(this, position, Toast.LENGTH_SHORT).show()
        startActivity(intent)
    }
}
