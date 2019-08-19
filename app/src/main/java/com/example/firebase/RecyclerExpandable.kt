package com.example.firebase

import android.os.Bundle
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
    var infArrayYear: ArrayList<DateInformationVO> = ArrayList()
    var infArrayMonth: ArrayList<DateInformationVO> = ArrayList()

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

            expandableListView!!.setOnGroupExpandListener {
                groupPosition -> Toast.makeText(applicationContext,
                (titleList as ArrayList<DateInformationVO>)[groupPosition].date.toString() + " List Expanded.",
                Toast.LENGTH_SHORT).show()
            }

            expandableListView!!.setOnGroupCollapseListener { groupPosition ->
                Toast.makeText(applicationContext, (titleList as ArrayList<DateInformationVO>)[groupPosition].date.toString() + " List Collapsed.", Toast.LENGTH_SHORT).show()
            }

            expandableListView!!.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
                Toast.makeText(applicationContext, "Clicked: " + (titleList as ArrayList<DateInformationVO>)[groupPosition].date
                        + " -> " + data[(titleList as ArrayList<DateInformationVO>)[groupPosition]]!![childPosition].month,
                    Toast.LENGTH_SHORT).show()
                //parentObserver2()
                false
            }
        }
    }

     private fun parentObserver2(){
         treeInformationViewModel.getAllDaysInformation().observe(this, androidx.lifecycle.Observer<List<DateInformationVO>>{ dateInf ->
             childObserver2(dateInf as ArrayList<DateInformationVO>)
         })
     }

    private fun childObserver2(parentArrayList: ArrayList<DateInformationVO>){
        treeInformationViewModel.getAllHoursInformation().observe(this, androidx.lifecycle.Observer<List<DateInformationVO>>{dateInf->
            infArrayYear = parentArrayList
            infArrayMonth = dateInf as ArrayList<DateInformationVO>
            fillInf(infArrayYear, dateInf)
        })
    }

    private fun parentObserver() {//Trae la informacion de la base de datos
        treeInformationViewModel.getAllYearInformation()
            .observe(this, androidx.lifecycle.Observer<List<DateInformationVO>>{ dateInf ->
                infArrayYear = dateInf as ArrayList<DateInformationVO>
            childObserver()
        })
    }

    private fun childObserver(){
        infArrayYear.forEach {
            treeInformationViewModel.getMonthsPerYear(it.date!!)
                .observe(this, androidx.lifecycle.Observer<List<DateInformationVO>> { dateInf ->
                    infArrayMonth = dateInf as ArrayList<DateInformationVO>
                    fillInf(infArrayYear, infArrayMonth)
                })
        }
    }

    private fun fillInf(parent: ArrayList<DateInformationVO>, child: ArrayList<DateInformationVO>){//Llena la informacion que se mostrará en el recycler expandible
        val listData = HashMap<DateInformationVO, List<DateInformationVO>>()
        val listMonth: ArrayList<DateInformationVO> = ArrayList()
        child.forEach {
            listMonth.add(it)
        }
        parent.forEach { year->
            listData[year] = listMonth as List<DateInformationVO>
        }
        data = listData
        fillExpListInformation()
    }
}