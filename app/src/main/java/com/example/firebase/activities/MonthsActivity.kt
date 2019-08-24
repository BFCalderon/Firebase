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
import kotlinx.android.synthetic.main.activity_months.*
import java.util.*

class MonthsActivity : AppCompatActivity(), TreeInformationAdapter.INotifyItemSelected {

    private lateinit var treeInformationViewModel: TreeInformationViewModel
    private var adapterInformationDates: TreeInformationAdapter?= null
    private var recyclerDateInfBD: RecyclerView?= null
    lateinit var yearParent: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_months)

        yearParent = this.intent.extras["year"].toString()
        monthInf.text = yearParent

        treeInformationViewModel = run {
            ViewModelProviders.of(this).get(TreeInformationViewModel::class.java)
        }
        readYears()
    }

    private fun readYears() {
        treeInformationViewModel.getMonthsPerYear(yearParent.toInt())
            .observe(this, androidx.lifecycle.Observer<List<DateInformationVO>> { dateInf ->
                startRecycler(dateInf as ArrayList<DateInformationVO>)
            })
    }

    private fun startRecycler(monthsList: ArrayList<DateInformationVO>){
        adapterInformationDates = TreeInformationAdapter(monthsList)
        adapterInformationDates!!.isMonth = true
        adapterInformationDates!!.isDay = false
        adapterInformationDates!!.isHour = false

        recyclerDateInfBD = recyclerMonths
        recyclerDateInfBD!!.layoutManager = LinearLayoutManager(this)
        recyclerDateInfBD!!.adapter = this.adapterInformationDates
        adapterInformationDates!!.iNotifyItemSelected = this
    }

    override fun sendChangeCoor(position: String) {
        val intent = Intent(this, DaysActivity::class.java)
        intent.putExtra("year", yearParent)
        intent.putExtra("month", position)
        Toast.makeText(this, position, Toast.LENGTH_SHORT).show()
        startActivity(intent)
    }
}
