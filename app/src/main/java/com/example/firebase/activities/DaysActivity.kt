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
import com.example.firebase.utils.ConstantValues
import com.example.firebase.valueobjects.DateInformationVO
import com.example.firebase.viewmodel.TreeInformationViewModel
import kotlinx.android.synthetic.main.activity_days.*
import java.util.*

class DaysActivity : AppCompatActivity(), TreeInformationAdapter.INotifyItemSelected {

    private lateinit var treeInformationViewModel: TreeInformationViewModel
    private var adapterInformationDates: TreeInformationAdapter?= null
    private var recyclerDateInfBD: RecyclerView?= null
    lateinit var yearParent: String
    lateinit var month: String
    private var constantValues: ConstantValues = ConstantValues()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_days)

        yearParent = this.intent.extras["year"].toString()
        month = this.intent.extras["month"].toString()
        dayInf.text = "${constantValues.getStringMont(month.toInt())} DE $yearParent"

        treeInformationViewModel = run {
            ViewModelProviders.of(this).get(TreeInformationViewModel::class.java)
        }
        readYears()
    }

    private fun readYears() {
        treeInformationViewModel.getDaysByMonths(yearParent.toInt(), month.toInt())
            .observe(this, androidx.lifecycle.Observer<List<DateInformationVO>> { dateInf ->
                startRecycler(dateInf as ArrayList<DateInformationVO>)
            })
    }

    private fun startRecycler(monthsList: ArrayList<DateInformationVO>){
        adapterInformationDates = TreeInformationAdapter(monthsList)
        adapterInformationDates!!.isMonth = false
        recyclerDateInfBD = recyclerDays
        recyclerDateInfBD!!.layoutManager = LinearLayoutManager(this)
        recyclerDateInfBD!!.adapter = this.adapterInformationDates
        adapterInformationDates!!.iNotifyItemSelected = this
    }

    override fun sendChangeCoor(position: String) {
        val intent = Intent(this, HoursActivity::class.java)
        intent.putExtra("year", yearParent)
        intent.putExtra("month", month)
        intent.putExtra("day", position)
        Toast.makeText(this, position, Toast.LENGTH_SHORT).show()
        startActivity(intent)
    }
}