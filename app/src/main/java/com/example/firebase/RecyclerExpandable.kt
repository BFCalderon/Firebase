package com.example.firebase

import android.os.Bundle
import android.os.Handler
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.firebase.valueobjects.DateInformationVO
import com.example.firebase.viewmodel.TreeInformationViewModel
import java.util.*
import kotlin.collections.ArrayList

class RecyclerExpandable : AppCompatActivity() {

    private lateinit var treeInformationViewModel: TreeInformationViewModel
    var infArrayParent: ArrayList<DateInformationVO> = ArrayList()
    var infArrayChild: ArrayList<ArrayList<DateInformationVO>> = ArrayList()

    private var actualYearSelected: Int ?= null
    private var actualMontSelected: Int ?= null

    private var expandableListView: ExpandableListView? = null
    private var adapter: ExpandableListAdapter? = null
    private var titleList: List<DateInformationVO> ? = null

    lateinit var data: HashMap<DateInformationVO, List<DateInformationVO>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.expandable_recycler_year_month)

        //SqlLite Start
        treeInformationViewModel = run {
            ViewModelProviders.of(this).get(TreeInformationViewModel::class.java)
        }
        parentObserver()
    }

    private fun fillExpListInformation(){//Infla la vista con la informacion y la muestra
        expandableListView = findViewById(R.id.expandableListView)
        if (expandableListView != null) {
            titleList = ArrayList(data.keys)
            adapter = com.tutorialwing.expandablelistview.ExpandableListAdapter(
                this, titleList as ArrayList<DateInformationVO>, data)
            expandableListView!!.setAdapter(adapter)

            expandableListView!!.setOnGroupExpandListener { groupPosition ->
                Toast.makeText(applicationContext,
                (titleList as ArrayList<DateInformationVO>)[groupPosition].date.toString() + " List Expanded.",
                Toast.LENGTH_SHORT).show()
                actualYearSelected = (titleList as ArrayList<DateInformationVO>)[groupPosition].date.toString().toInt()
            }

            expandableListView!!.setOnGroupCollapseListener { groupPosition ->
                Toast.makeText(applicationContext, (titleList as ArrayList<DateInformationVO>)[groupPosition].date.toString() + " List Collapsed.", Toast.LENGTH_SHORT).show()
            }

            expandableListView!!.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
                Toast.makeText(applicationContext, "Clicked: " + (titleList as ArrayList<DateInformationVO>)[groupPosition].date
                        + " -> " + data[(titleList as ArrayList<DateInformationVO>)[groupPosition]]!![childPosition].date,
                    Toast.LENGTH_SHORT).show()
                actualMontSelected = data[(titleList as ArrayList<DateInformationVO>)[groupPosition]]!![childPosition].date
                parentObserver2()
                false
            }
        }
    }

     private fun parentObserver2(){
         infArrayParent.clear()
         treeInformationViewModel.getDaysByMonths(this.actualYearSelected!!, this.actualMontSelected!!)
             .observe(this, androidx.lifecycle.Observer<List<DateInformationVO>> { dateInf ->
                 infArrayParent = dateInf as ArrayList<DateInformationVO>
                 childObserver2()
             })
     }

    private fun childObserver2(){
        var dayIterator = 0
        infArrayParent.forEach {
            treeInformationViewModel.getSpecificHours(this.actualYearSelected!!, this.actualMontSelected!!, dayIterator)
                .observe(this, androidx.lifecycle.Observer<List<DateInformationVO>> { dateInf ->
                    infArrayChild.add(dateInf as ArrayList<DateInformationVO>)
                    dayIterator++
                    if(infArrayParent.size == dayIterator){
                        fillInf(infArrayParent, infArrayChild)
                    }
                })
            }
        dayIterator = 0
    }

    private fun parentObserver() {//Trae la informacion de la base de datos
        treeInformationViewModel.getAllYearInformation()
            .observe(this, androidx.lifecycle.Observer<List<DateInformationVO>>{ dateInf ->
                infArrayParent = dateInf as ArrayList<DateInformationVO>
            childObserver()
        })
    }

    private fun childObserver(){
        var iterator = 0
        infArrayParent.forEach { year ->
            treeInformationViewModel.getMonthsPerYear(year.date!!)
                .observe(this, androidx.lifecycle.Observer<List<DateInformationVO>> { dateInf ->
                    infArrayChild.add(dateInf as ArrayList<DateInformationVO>)
                    iterator++
                    if (iterator >= infArrayParent.size) {
                        fillInf(infArrayParent, infArrayChild)
                    }
                })
            }
        iterator = 0
    }

    private fun fillInf(parent: ArrayList<DateInformationVO>, child: ArrayList<ArrayList<DateInformationVO>>){//Llena la informacion que se mostrará en el recycler expandible
        val listData = HashMap<DateInformationVO, List<DateInformationVO>>()

        var iterator = 0
        parent.forEach { year->
            listData[year] = child[iterator] as List<DateInformationVO>
            iterator++
        }
        data = listData
        fillExpListInformation()
    }
}