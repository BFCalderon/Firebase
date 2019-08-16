package com.example.firebase

import android.os.Bundle
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.firebase.valueobjects.DateInformationVO
import com.example.firebase.viewmodel.TreeInformationViewModel
import com.tutorialwing.expandablelistview.CustomExpandableListAdapter
import java.lang.reflect.Array
import java.util.*
import kotlin.collections.ArrayList

class ExpandableRecyclerYearMonth : AppCompatActivity() {

    private lateinit var treeInformationViewModel: TreeInformationViewModel
    var infArrayYear: ArrayList<DateInformationVO> = ArrayList()
    var infArrayMonth: ArrayList<DateInformationVO> = ArrayList()

    internal var expandableListView: ExpandableListView? = null
    internal var adapter: ExpandableListAdapter? = null
    internal var titleList: List<String> ? = null

    lateinit var data: HashMap<String, List<String>>
        //get() {

        //}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.expandable_recycler_year_month)

        //SqlLite Start
        treeInformationViewModel = run {
            ViewModelProviders.of(this).get(TreeInformationViewModel::class.java)
        }

        addObserver()
    }

    private fun fillExpListYearMonth(){//Infla la vista con la informacion y la muestra
        expandableListView = findViewById(R.id.expandableListView)
        if (expandableListView != null) {
            titleList = ArrayList(data.keys)
            adapter = CustomExpandableListAdapter(this, titleList as ArrayList<String>, data)
            expandableListView!!.setAdapter(adapter)

            expandableListView!!.setOnGroupExpandListener {
                    groupPosition -> Toast.makeText(applicationContext, (titleList as ArrayList<String>)[groupPosition] + " List Expanded.", Toast.LENGTH_SHORT).show()
            }

            expandableListView!!.setOnGroupCollapseListener { groupPosition ->
                Toast.makeText(applicationContext, (titleList as ArrayList<String>)[groupPosition] + " List Collapsed.", Toast.LENGTH_SHORT).show()
            }

            expandableListView!!.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
                Toast.makeText(applicationContext, "Clicked: " + (titleList as ArrayList<String>)[groupPosition]
                        + " -> " + data[(titleList as ArrayList<String>)[groupPosition]]!!.get(childPosition),
                    Toast.LENGTH_SHORT).show()
                false
            }
        }
    }

    private fun addObserver() {//Trae la informacion de la base de datos
        treeInformationViewModel.getAllYearInformation().observe(this, androidx.lifecycle.Observer<List<DateInformationVO>>{ dateInf ->
            infArrayYear = dateInf as ArrayList<DateInformationVO>
            if(!infArrayMonth.isNullOrEmpty()){
                fillInf(infArrayYear, infArrayMonth)
            }
        })
        treeInformationViewModel.getAllMonthInformation().observe(this, androidx.lifecycle.Observer<List<DateInformationVO>>{dateInf->
            infArrayMonth = dateInf as ArrayList<DateInformationVO>
            if(!infArrayYear.isNullOrEmpty()){
                fillInf(infArrayYear, infArrayMonth)
            }
        })
    }

    private fun fillInf(year: ArrayList<DateInformationVO>, month: ArrayList<DateInformationVO>){//Llena la informacion que se mostrará en el recycler expandible
        val listData = HashMap<String, List<String>>()
        val listMonth: ArrayList<String> = ArrayList()

        month.forEach {
            listMonth.add(it.month.toString())
        }

        year.forEach {year->
            listData["${year.date}"] = listMonth as List<String>
        }
        data = listData
        fillExpListYearMonth()
    }
}