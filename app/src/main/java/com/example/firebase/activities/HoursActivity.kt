package com.example.firebase.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase.R
import com.example.firebase.adapters.TreeInformationAdapter
import com.example.firebase.utils.ConstantValues
import com.example.firebase.valueobjects.DateInformationVO
import com.example.firebase.viewmodel.TreeInformationViewModel
import kotlinx.android.synthetic.main.activity_hours.*
import java.util.*

class HoursActivity : AppCompatActivity(), TreeInformationAdapter.INotifyItemSelected {

    private lateinit var treeInformationViewModel: TreeInformationViewModel
    private var adapterInformationDates: TreeInformationAdapter?= null
    private var recyclerDateInfBD: RecyclerView?= null
    lateinit var yearParent: String
    lateinit var month: String
    lateinit var day: String
    private var constantValues: ConstantValues = ConstantValues()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hours)

        yearParent = this.intent.extras["year"].toString()
        month = this.intent.extras["month"].toString()
        day = this.intent.extras["day"].toString()
        hourInf.text = "${constantValues.getStringMont(month.toInt())} $day DE $yearParent"

        treeInformationViewModel = run {
            ViewModelProviders.of(this).get(TreeInformationViewModel::class.java)
        }
        readYears()
    }

    private fun readYears() {
        treeInformationViewModel.getSpecificHours(yearParent.toInt(), month.toInt(), day.toInt())
            .observe(this, androidx.lifecycle.Observer<List<DateInformationVO>> { dateInf ->
                startRecycler(dateInf as ArrayList<DateInformationVO>)
            })
    }
    private fun startRecycler(monthsList: ArrayList<DateInformationVO>){
        adapterInformationDates = TreeInformationAdapter(monthsList)
        adapterInformationDates!!.isMonth = false
        adapterInformationDates!!.isDay = false
        adapterInformationDates!!.isHour = true

        recyclerDateInfBD = recyclerHours
        recyclerDateInfBD!!.layoutManager = LinearLayoutManager(this)
        recyclerDateInfBD!!.adapter = this.adapterInformationDates
        adapterInformationDates!!.iNotifyItemSelected = this
    }

    override fun sendChangeCoor(position: String) {
        Toast.makeText(this, position, Toast.LENGTH_SHORT).show()
    }
}