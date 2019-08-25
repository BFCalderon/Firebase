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

    /*private lateinit var pieChart: PieChart
    private lateinit var  barChart: BarChart
    private lateinit var  lineChart: LineChart
    private lateinit var  bubbleChart: BubbleChart
    private lateinit var  radarChart: RadarChart
    private lateinit var  scatterChart: ScatterChart
    private lateinit var  candleStickChart: CandleStickChart

    //Eje X
    private val months: List<String> = listOf("Enero","Febrero","Marzo","Abril","Mayo")
    //Eje Y


    //Grafica de burbuja(Bubble) 3 dato (Este define el tamaño de la burbuja)
    private val purchase = listOf(50,400,100,200,500)

    //Grafica de Radar(Radar)
    //Los criterios que se evaluaran el la grafica
    private val variable = listOf("Speed","Durability","Comfort","Power","Space")
    //Valor para los criterios en un auto Chevrolet
    private val valueChevrolet = listOf(5,6,10,4,9)
    //Valor para los criterios en un auto Jeep
    private val valueJeep = listOf(10,5,3,4,5)


    //Grafica de Vela(Candlestick)
    //Es estos arreglos el:
    //Primero es altista (abrio en 4 y cerro en 6)
    //Segunfo es bajista (abrio en 7 y cerro en 4)
    //Tercero es altista (abrio en 6 y cerro en 8)
    //Cuarto es bajista (abrio en 6 y cerro en 3)
    //Quinto es altista (abrio en 2 y cerro en 5)

    //Maximo
    private val shadowHigh = listOf(7, 10, 9,  8,  6)
    //Minimo
    private val shadowLow = listOf(3, 5,  4,  2,  0)
    //Abrir
    private val open = listOf(4, 7,  6,  6,  2)
    //Cerra
    private val close = listOf(6, 4,  8,  3,  5)

*/
    //Colors
    private val colors = listOf(/*Color.BLACK,*/R.color.colorPrimaryDark/*,Color.GREEN,Color.BLUE,Color.LTGRAY*/)
    private val sale = listOf(25,20,38,10,15)
    val months = listOf<String>("ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graphic_class)

        /*barChart=(BarChart)findViewById(R.id.barChart);
        pieChart=(PieChart) findViewById(R.id.pieChart);
        lineChart = (LineChart) findViewById(R.id.lineChart);
        bubbleChart = (BubbleChart) findViewById(R.id.bubbleChart);
        radarChart = (RadarChart) findViewById(R.id.radarChart);
        scatterChart = (ScatterChart) findViewById(R.id.scatterChart);
        candleStickChart = (CandleStickChart) findViewById(R.id.candleStickChart);*/
        //createCharts();

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

    fun createCharts() {
        barChart.setDrawGridBackground(true)
        barChart.setDrawBarShadow(true)
        barChart.data = getBarData()
        barChart.invalidate()
        barChart.legend.isEnabled = false
        axisX(barChart.xAxis)
        axisLeft(barChart.axisLeft)
        axisRight(barChart.axisRight)
    }


/*

    //Carasteristicas comunes en las graficas
    private fun getSameChart(
        chart: Chart<*>,
        description: String,
        textColor: Int,
        background: Int,
        animateY: Int,
        leyenda: Boolean
            ): Chart<*> {
        chart.description.text = description
        chart.description.textColor = textColor
        chart.description.textSize = 15f
        chart.setBackgroundColor(background)
        chart.animateY(animateY)

        //Validar porque la grafica de radar y dispersion tiene dos datos especificos y la leyenda se crea de acuerdo a esos datos.
        if (leyenda)
            legend(chart)
        return chart
    }

    private fun legend(chart: Chart<*>) {
        val legend = chart.legend
        legend.form = Legend.LegendForm.CIRCLE
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER

        val entries: ArrayList<LegendEntry> = ArrayList()
        for (i in 0 until months.size) {
            val entry = LegendEntry()
            entry.formColor = colors[i]
            entry.label = months[i]
            entries.add(entry)
        }
        legend.setCustom(entries)
    }

    private fun getBarEntries(): ArrayList<BarEntry> {
        val entries: ArrayList<BarEntry> = ArrayList()
        for (i in 0 until sale.size)
            entries.add(BarEntry(i.toFloat(), sale[i].toFloat()))
        return entries
    }

    private fun getPieEntries(): ArrayList<PieEntry> {
        val entries: ArrayList<PieEntry> = ArrayList()
        for (i in 0 until sale.size)
            entries.add(PieEntry(sale[i].toFloat()))
        return entries
    }

    private fun getLineEntries(): ArrayList<Entry> {
        val entries: ArrayList<Entry> = ArrayList()
        for (i in 0 until sale.size)
            entries.add(Entry(i.toFloat(), sale[i].toFloat()))
        return entries
    }

    private fun getBubbleEntries(): ArrayList<BubbleEntry> {
        //Grafica de burbuja necesita 3 valores
        val entries: ArrayList<BubbleEntry> = ArrayList()
        for (i in 0 until sale.size)
            entries.add(BubbleEntry(i.toFloat(), sale[i].toFloat(), purchase[i].toFloat()))
        return entries
    }

    private fun getRadarEntriesChevrolet(): ArrayList<RadarEntry> {
        val entries: ArrayList<RadarEntry> = ArrayList()
        for (i in 0 until valueChevrolet.size)
            entries.add(RadarEntry(valueChevrolet[i].toFloat()))
        return entries
    }

    private fun getRadarEntriesJeep(): ArrayList<RadarEntry> {
        val entries: ArrayList<RadarEntry> = ArrayList()
        for (i in 0 until valueJeep.size)
            entries.add(RadarEntry(valueJeep[i].toFloat()))
        return entries
    }

    */
/*private fun getVariable(): ArrayList<String> {
        val entries: ArrayList<String> = ArrayList()
        entries.addAll(Arrays.asList(variable))
        return entries
    }*//*


    private fun getVariable(): ArrayList<String> {
        val entries: ArrayList<String> = ArrayList()
        entries.addAll(variable)
        return entries
    }

    private fun getScatterEntries(): ArrayList<Entry> {
        val entries: ArrayList<Entry> = ArrayList()
        for (i in 0 until sale.size)
            entries.add(Entry(i.toFloat(), sale[i].toFloat()))
        return entries
    }

    private fun getCandleEntries(): ArrayList<CandleEntry> {
        val entries: ArrayList<CandleEntry> = ArrayList()
        for (i in 0 until sale.size)
            entries.add(CandleEntry(i.toFloat(), shadowHigh[i].toFloat(), shadowLow[i].toFloat(), open[i].toFloat(), close[i].toFloat()))
        return entries
    }

    //Eje horizontal o eje X
    private fun axisX(axis: XAxis) {
        axis.isGranularityEnabled = true
        axis.position = XAxis.XAxisPosition.BOTTOM
        axis.valueFormatter = IndexAxisValueFormatter(months)
    }

    //Eje Vertical o eje Y lado izquierdo
    private fun axisLeft(axis: YAxis) {
        axis.spaceTop = 30f
        axis.axisMinimum = 0f
        axis.granularity = 20f
    }

    //Eje Vertical o eje Y lado Derecho
    private fun axisRight(axis: YAxis) {
        axis.isEnabled = false
    }

    //Crear graficas
    fun createCharts() {
        //BarChart
        //barChart.pieChart(getSameChart(barChart, "Series", Color.RED, Color.CYAN, 3000, true) as BarChart)
        //barChart = getSameChart(barChart, "Series", Color.RED, Color.CYAN, 3000, true) as BarChart
        barChart.setDrawGridBackground(true)
        barChart.setDrawBarShadow(true)
        barChart.setData(getBarData())
        barChart.invalidate()
        barChart.getLegend().setEnabled(false)
        axisX(barChart.getXAxis())
        axisLeft(barChart.getAxisLeft())
        axisRight(barChart.getAxisRight())

        //PieChart
        //pieChart = getSameChart(pieChart, "Ventas", Color.GRAY, Color.MAGENTA, 3000, true) as PieChart
        pieChart.setHoleRadius(10f)
        pieChart.setTransparentCircleRadius(12f)
        pieChart.setData(getPieData())
        pieChart.invalidate()
        pieChart.setDrawHoleEnabled(false)

        //LineChart
        //lineChart = getSameChart(lineChart, "Ventas", Color.BLUE, Color.YELLOW, 3000, true) as LineChart
        lineChart.setData(getLineData())
        lineChart.invalidate()
        axisX(lineChart.getXAxis())
        axisLeft(lineChart.getAxisLeft())
        axisRight(lineChart.getAxisRight())

        //BubbleChart
        //bubbleChart = getSameChart(bubbleChart, "Compras", Color.GRAY, Color.YELLOW, 3000, true) as BubbleChart
        bubbleChart.setData(getBubbleData())
        bubbleChart.invalidate()
        axisX(bubbleChart.getXAxis())
        axisLeft(bubbleChart.getAxisLeft())
        axisRight(bubbleChart.getAxisRight())

        //Para que el eje X inicie de -0.5 y termine en 5 con esto las burbujas no se veran cortada(Borrar y ejecutar para ver la diferencia)
        bubbleChart.getXAxis().setAxisMinimum(-1f)
        bubbleChart.getXAxis().setAxisMaximum(months.size.toFloat())


        //ScatterChart
        scatterChart.ScatterShape = getSameChart(scatterChart, "Temperaturas en Mèxico", Color.BLACK, Color.LTGRAY, 3000, false) as ScatterChart
        scatterChart.data = getScatterData()
        axisX(scatterChart.getXAxis())
        axisLeft(scatterChart.getAxisLeft())
        axisRight(scatterChart.getAxisRight())

        //Para que el eje X inicie de -0.5 y termine en 5 con esto los puntos no se veran cortada(Borrar y ejecutar para ver la diferencia)
        scatterChart.getXAxis().setAxisMinimum(-1f)
        scatterChart.getXAxis().setAxisMaximum(months.size.toFloat())


        //CandleStickChart
        */
/*candleStickChart = getSameChart(
            candleStickChart,
            "Oferta-Demanda Oro",
            Color.BLUE,
            Color.LTGRAY,
            3000,
            true
        ) as CandleStickChart*//*

        candleStickChart.setData(getCandleData())

        //Desactivar la leyenda porque no se maneja
        candleStickChart.getLegend().setEnabled(false)
        axisX(candleStickChart.getXAxis())
        axisRight(candleStickChart.getAxisRight())
        candleStickChart.invalidate()


        //RadarChart

        //En radar se valido la leyenda porque no podemos perzonalizarlo la leyenda se crea de acuerdo a los datos que se tienen dentro de la grafica.
        //radarChart = getSameChart(radarChart, "Mejor carro", Color.YELLOW, Color.LTGRAY, 3000, false) as RadarChart
        radarChart.setData(getRadarData())
        radarChart.invalidate()
        axisX(radarChart.getXAxis())

    }

    //Carasteristicas comunes en dataset
    private fun getDataSame(dataSet: DataSet<*>): DataSet<*> {
        dataSet.setColors(colors)
        dataSet.valueTextColor = Color.WHITE
        dataSet.valueTextSize = 10f
        return dataSet
    }

    private fun getBarData(): BarData {
        val barDataSet = getDataSame(BarDataSet(getBarEntries(), "")) as BarDataSet
        barDataSet.barShadowColor = Color.GRAY
        val barData = BarData(barDataSet)
        barData.barWidth = 0.45f
        return barData
    }

    private fun getPieData(): PieData {
        val pieDataSet = getDataSame(PieDataSet(getPieEntries(), "")) as PieDataSet
        pieDataSet.sliceSpace = 2f
        pieDataSet.valueFormatter = PercentFormatter()
        return PieData(pieDataSet)
    }

    private fun getLineData(): LineData {
        val lineDataSet = getDataSame(LineDataSet(getLineEntries(), "")) as LineDataSet
        lineDataSet.lineWidth = 2.5f
        //Color de los circulos de la grafica
        lineDataSet.setCircleColors(colors)
        //Tamaño de los circulos de la grafica
        lineDataSet.circleRadius = 5f
        //Sombra grafica
        lineDataSet.setDrawFilled(true)
        //Estilo de la linea picos(linear) o curveada(cubic) cuadrada(Stepped)
        lineDataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
        return LineData(lineDataSet)
    }

    private fun getBubbleData(): BubbleData {
        val bubbleDataSet = getDataSame(BubbleDataSet(getBubbleEntries(), "")) as BubbleDataSet
        return BubbleData(bubbleDataSet)
    }

    private fun getRadarData(): RadarData {

        val chevrolet = getDataSame(RadarDataSet(getRadarEntriesChevrolet(), "Chevrolet")) as RadarDataSet
        //Definimos un color especial para chevrolet para no cargar el arreglo de colores
        chevrolet.color = Color.MAGENTA

        //Definimos un color especial para jeep para no cargar el arreglo de colores
        val jeep = getDataSame(RadarDataSet(getRadarEntriesJeep(), "Jeep")) as RadarDataSet
        jeep.color = Color.RED

        val dataSets = ArrayList<IRadarDataSet>()
        dataSets.add(chevrolet)
        dataSets.add(jeep)

        val data = RadarData(dataSets)
        data.labels = getVariable()
        return data
    }

    private fun getScatterData(): ScatterData {
        val scatterDataSet = getDataSame(ScatterDataSet(getScatterEntries(), "Temperatura")) as ScatterDataSet
        scatterDataSet.setScatterShape(ScatterChart.ScatterShape.CIRCLE)
        scatterDataSet.color = Color.MAGENTA
        return ScatterData(scatterDataSet)
    }

    private fun getCandleData(): CandleData {
        val candleDataSet = getDataSame(CandleDataSet(getCandleEntries(), "")) as CandleDataSet
        candleDataSet.increasingColor = Color.GREEN
        candleDataSet.decreasingColor = Color.RED
        candleDataSet.shadowColorSameAsCandle = true
        return CandleData(candleDataSet)
    }
*/

}
