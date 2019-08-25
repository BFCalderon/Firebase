package com.example.firebase.activities

import android.graphics.Color
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
import com.github.mikephil.charting.charts.Chart
import com.github.mikephil.charting.components.*
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.DataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
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

    private val colors = listOf(R.color.colorPrimaryDark)
    private var eficiency: ArrayList<Int> = ArrayList()
    private var hours: ArrayList<String> = ArrayList()

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
        monthsList.forEach { year->
            this.hours.add(year.date.toString()+" : 00")
            this.eficiency.add(year.efficiency!!.toInt())
        }
        setbarChartHours()
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
    //Gestor de grafico de barras
    private fun setbarChartHours() {

        barChartHours.data = getBarData() // set the data and list of lables into chart
        legend(barChartHours)
        val description = Description()
        description.text = ""
        barChartHours.description =  description // set the description
        createCharts()

        barChartHours.animateY(1000)
    }

    private fun getBarData(): BarData {
        val barDataSet = getDataSame(BarDataSet(getBarEntries(), "Brayan")) as BarDataSet
        barDataSet.barShadowColor = Color.argb(50,96,124,97)
        val barData = BarData(barDataSet)
        barData.barWidth = 0.5f
        return barData
    }

    //Carasteristicas comunes en dataset
    private fun getDataSame(dataSet: DataSet<*>): DataSet<*> {
        dataSet.colors = colors
        dataSet.valueTextColor = R.color.colorPrimaryDark
        dataSet.valueTextSize = 10f
        return dataSet
    }

    private fun getBarEntries(): ArrayList<BarEntry> {
        val entries: ArrayList<BarEntry> = ArrayList()
        for (i in 0 until eficiency.size)
            entries.add(BarEntry(i.toFloat(), eficiency[i].toFloat()))
        return entries
    }
    private fun legend(chart: Chart<*>) {
        val legend = chart.legend
        legend.form = Legend.LegendForm.CIRCLE
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER

        val entries: ArrayList<LegendEntry> = ArrayList()
        for (i in 0 until hours.size) {
            val entry = LegendEntry()
            entry.formColor = colors[0]
            entry.label = hours[i]
            entries.add(entry)
        }
        legend.setCustom(entries)
    }
    //Eje horizontal o eje X
    private fun axisX(axis: XAxis) {
        axis.isGranularityEnabled = true
        axis.position = XAxis.XAxisPosition.BOTTOM
        axis.valueFormatter = IndexAxisValueFormatter(hours)
    }

    //Eje Vertical o eje Y lado izquierdo
    private fun axisLeft(axis: YAxis) {
        axis.spaceTop = 10f
        axis.axisMinimum = 0f
        axis.granularity = 1f
        axis.axisMaximum = 100f
    }

    //Eje Vertical o eje Y lado Derecho
    private fun axisRight(axis: YAxis) {
        axis.isEnabled = false
    }

    private fun createCharts() {
        barChartHours.setDrawGridBackground(true)
        barChartHours.setDrawBarShadow(true)
        barChartHours.data = getBarData()
        barChartHours.invalidate()
        barChartHours.legend.isEnabled = false
        axisX(barChartHours.xAxis)
        axisLeft(barChartHours.axisLeft)
        axisRight(barChartHours.axisRight)
    }
}