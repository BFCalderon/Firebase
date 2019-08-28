package com.example.firebase.activities

import android.content.Intent
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
import kotlinx.android.synthetic.main.activity_days.*
import java.util.*

class DaysActivity : AppCompatActivity(), TreeInformationAdapter.INotifyItemSelected {

    private lateinit var treeInformationViewModel: TreeInformationViewModel
    private var adapterInformationDates: TreeInformationAdapter?= null
    private var recyclerDateInfBD: RecyclerView?= null
    lateinit var yearParent: String
    lateinit var month: String
    private var constantValues: ConstantValues = ConstantValues()

    private val colors = listOf(R.color.colorPrimaryDark)
    private var eficiency: ArrayList<Int> = ArrayList()
    private var days: ArrayList<String> = ArrayList()

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

    private fun startRecycler(daysList: ArrayList<DateInformationVO>){
        days.clear()
        eficiency.clear()
        daysList.forEach { year->
            this.days.add(year.date.toString())
            this.eficiency.add(year.efficiency!!.toInt())
        }
        setbarChartDays()
        
        adapterInformationDates = TreeInformationAdapter(daysList)
        adapterInformationDates!!.isMonth = false
        adapterInformationDates!!.isDay = true
        adapterInformationDates!!.isHour = false

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

    //Gestor de grafico de barras
    private fun setbarChartDays() {

        barChartDays.data = getBarData() // set the data and list of lables into chart
        legend(barChartDays)
        val description = Description()
        description.text = ""
        barChartDays.description =  description // set the description
        createCharts()

        barChartDays.animateY(1000)
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
        for (i in 0 until days.size) {
            val entry = LegendEntry()
            entry.formColor = colors[0]
            entry.label = days[i]
            entries.add(entry)
        }
        legend.setCustom(entries)
    }
    //Eje horizontal o eje X
    private fun axisX(axis: XAxis) {
        axis.isGranularityEnabled = true
        axis.position = XAxis.XAxisPosition.BOTTOM
        axis.valueFormatter = IndexAxisValueFormatter(days)
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
        barChartDays.setDrawGridBackground(true)
        barChartDays.setDrawBarShadow(true)
        barChartDays.data = getBarData()
        barChartDays.invalidate()
        barChartDays.legend.isEnabled = false
        axisX(barChartDays.xAxis)
        axisLeft(barChartDays.axisLeft)
        axisRight(barChartDays.axisRight)
    }
}