package com.example.firebase.activities

import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.firebase.R
import com.github.mikephil.charting.charts.CandleStickChart
import com.github.mikephil.charting.charts.Chart
import com.github.mikephil.charting.charts.ScatterChart
import com.github.mikephil.charting.components.*
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet
import kotlinx.android.synthetic.main.activity_graphic_class.*
import kotlinx.android.synthetic.main.activity_graphic_class.view.*


import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.MPPointF
import java.util.*


class GraphicClass : AppCompatActivity() {

    //Colors
    private val colors = listOf(/*Color.BLACK,*/R.color.colorPrimaryDark/*,Color.GREEN,Color.BLUE,Color.LTGRAY*/)
    private val sale = listOf(25,20,38,10,15)
    val months = listOf("ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graphic_class)

        setBarChart()
    }

    private fun setBarChart() {

        barChart.data = getBarData() // set the data and list of lables into chart
        legend(barChart)
        val description = Description()
        description.text = ""
        barChart.description =  description // set the description
        createCharts()

        barChart.animateY(1000)
    }

    private fun getBarData(): BarData {
        val barDataSet = getDataSame(BarDataSet(getBarEntries(), "Brayan")) as BarDataSet
        barDataSet.barShadowColor = Color.argb(50,96,124,97)
        val barData = BarData(barDataSet)
        barData.barWidth = 0.4f
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
        for (i in 0 until sale.size)
            entries.add(BarEntry(i.toFloat(), sale[i].toFloat()))
        return entries
    }
    private fun legend(chart: Chart<*>) {
        val legend = chart.legend
        legend.form = Legend.LegendForm.CIRCLE
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER

        val entries: ArrayList<LegendEntry> = ArrayList()
        for (i in 0 until months.size) {
            val entry = LegendEntry()
            entry.formColor = colors[0]
            entry.label = months[i]
            entries.add(entry)
        }
        legend.setCustom(entries)
    }
    //Eje horizontal o eje X
    private fun axisX(axis: XAxis) {
        axis.isGranularityEnabled = true
        axis.position = XAxis.XAxisPosition.BOTTOM
        axis.valueFormatter = IndexAxisValueFormatter(months)
    }

    //Eje Vertical o eje Y lado izquierdo
    private fun axisLeft(axis: YAxis) {
        axis.spaceTop = 10f
        axis.axisMinimum = 0f
        axis.granularity = 1f
    }

    //Eje Vertical o eje Y lado Derecho
    private fun axisRight(axis: YAxis) {
        axis.isEnabled = false
    }

    private fun createCharts() {
        barChart.setDrawGridBackground(true)
        barChart.setDrawBarShadow(true)
        barChart.data = getBarData()
        barChart.invalidate()
        barChart.legend.isEnabled = false
        axisX(barChart.xAxis)
        axisLeft(barChart.axisLeft)
        axisRight(barChart.axisRight)
    }
}
