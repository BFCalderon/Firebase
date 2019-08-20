package com.tutorialwing.expandablelistview

import android.content.Context
import android.graphics.Typeface
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.example.firebase.R
import com.example.firebase.math.MathUtility
import com.example.firebase.valueobjects.DateInformationVO
import kotlinx.android.synthetic.main.activity_register.view.*

import java.util.HashMap

class ExpandableListAdapter internal constructor(
    private val context: Context,/* private val titleList: List<DateInformationVO>,*/
    private val parentList: List<DateInformationVO>, private val childList: ArrayList<List<DateInformationVO>>) : BaseExpandableListAdapter() {

    var flagYD = 1234
    var mathUtility: MathUtility = MathUtility()
    var childExpandedPosition: Int ?= null

    override fun getChild(listPosition: Int, expandedListPosition: Int): DateInformationVO {
        return childList[listPosition][expandedListPosition]
        //return this.dataList[this.titleList[listPosition]]!![expandedListPosition]
    }

    override fun getChildId(listPosition: Int, expandedListPosition: Int): Long {
        return expandedListPosition.toLong()
    }

    override fun getChildView(listPosition: Int, expandedListPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val expandedListText = getChild(listPosition, expandedListPosition)
        if (convertView == null) {
            val layoutInflater = this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.child_year_month, null)
        }
        val childTitleName = convertView!!.findViewById<TextView>(R.id.titleName)
        val childPower = convertView.findViewById<TextView>(R.id.powerText)
        val childEficiency = convertView.findViewById<TextView>(R.id.eficiencyText)
        val childEficiencyBar  = convertView.findViewById<ProgressBar>(R.id.eficiencyBarChlid)

        //dataList

        childTitleName.text = if(flagYD > 31){ "${expandedListText.date}" }else{ "${expandedListText.date}"}
        childPower.text = "${mathUtility.roundDouble(expandedListText.power!!.toDouble(), 2)}W"
        childEficiency.text = "${mathUtility.roundDouble(expandedListText.efficiency!!.toDouble(),2)}%"
        childEficiencyBar.progress = expandedListText.efficiency!!.toInt()

        //childPower.setTypeface(null, Typeface.BOLD)//Cambia el tipo de letra
        return convertView
    }

    override fun getChildrenCount(listPosition: Int): Int {
        return childList[childExpandedPosition!!].size
    }

    override fun getGroup(listPosition: Int): DateInformationVO {
        return this.parentList[listPosition]
    }

    override fun getGroupCount(): Int {
        return this.parentList.size
    }

    override fun getGroupId(listPosition: Int): Long {
        childExpandedPosition = listPosition
        return listPosition.toLong()
    }

    override fun getGroupView(listPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val listTitle = getGroup(listPosition)
        if (convertView == null) {
            val layoutInflater = this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.parent_year_month, null)
        }
        val parentTitleName = convertView!!.findViewById<TextView>(R.id.listTitleYear)
        val parentPower = convertView.findViewById<TextView>(R.id.powerText)
        val parentEficiency = convertView.findViewById<TextView>(R.id.eficiencyText)
        val parentEficiencyBar = convertView.findViewById<ProgressBar>(R.id.eficiencyBarParent)

        parentTitleName.text = listTitle.date!!.toInt().toString()
        parentPower.text = "${mathUtility.roundDouble(listTitle.power!!.toDouble(), 2)}W"
        parentEficiency.text = "${mathUtility.roundDouble(listTitle.efficiency!!.toDouble(),2)}%"
        parentEficiencyBar.progress = listTitle.efficiency!!.toInt()

        parentTitleName.setTypeface(null, Typeface.BOLD)
        parentPower.setTypeface(null, Typeface.BOLD)
        parentEficiency.setTypeface(null, Typeface.BOLD)

        //parentEficiency.post { parentEficiency.text = Editable.Factory.getInstance().newEditable((listTitle.date.toString())) }
        //listTitleTextView.text = listTitle

        return convertView
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun onGroupExpanded(groupPosition: Int) {
        flagYD = (parentList as ArrayList<DateInformationVO>)[groupPosition].date.toString().toInt()
        Toast.makeText(context, parentList[groupPosition].date.toString() + " List Expanded.", Toast.LENGTH_SHORT).show()
    }

    override fun onGroupCollapsed(groupPosition: Int) {
        Toast.makeText(context, (parentList as ArrayList<DateInformationVO>)[groupPosition].date.toString() + " List Collapsed.", Toast.LENGTH_SHORT).show()
    }

    override fun isChildSelectable(listPosition: Int, expandedListPosition: Int): Boolean {
        return true
    }
}
