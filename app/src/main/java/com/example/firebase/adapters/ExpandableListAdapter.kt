package com.tutorialwing.expandablelistview

import android.content.Context
import android.graphics.Typeface
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.example.firebase.R
import com.example.firebase.valueobjects.DateInformationVO

import java.util.HashMap

class ExpandableListAdapter internal constructor(private val context: Context, private val titleList: List<DateInformationVO>, private val dataList: HashMap<DateInformationVO, List<DateInformationVO>>) : BaseExpandableListAdapter() {

    override fun getChild(listPosition: Int, expandedListPosition: Int): DateInformationVO {
        return this.dataList[this.titleList[listPosition]]!![expandedListPosition]
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
        val expandedListTextView = convertView!!.findViewById<TextView>(R.id.expandedListMonth)
        //expandedListTextView.text = expandedListText.date
        expandedListTextView.setTypeface(null, Typeface.BOLD)
        expandedListTextView.post { expandedListTextView.text = Editable.Factory.getInstance().newEditable(expandedListText.month) }

        val expandedListTextViewPower = convertView!!.findViewById<TextView>(R.id.powerTextYear)
        expandedListTextViewPower.setTypeface(null, Typeface.BOLD)
        expandedListTextViewPower.post { expandedListTextViewPower.text = Editable.Factory.getInstance().newEditable(expandedListText.power.toString()) }
        return convertView
    }

    override fun getChildrenCount(listPosition: Int): Int {
        return this.dataList[this.titleList[listPosition]]!!.size
    }

    override fun getGroup(listPosition: Int): DateInformationVO {
        return this.titleList[listPosition]
    }

    override fun getGroupCount(): Int {
        return this.titleList.size
    }

    override fun getGroupId(listPosition: Int): Long {
        return listPosition.toLong()
    }

    override fun getGroupView(listPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val listTitle = getGroup(listPosition)
        if (convertView == null) {
            val layoutInflater = this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.parent_year_month, null)
        }
        val listTitleTextView = convertView!!.findViewById<TextView>(R.id.listTitleYear)
        listTitleTextView.setTypeface(null, Typeface.BOLD)
        listTitleTextView.post { listTitleTextView.text = Editable.Factory.getInstance().newEditable((listTitle.date.toString())) }
        //listTitleTextView.text = listTitle

        return convertView
    }

    override fun hasStableIds(): Boolean {
        return false
    }
//
//    override fun onGroupExpanded(groupPosition: Int) {
//        Toast.makeText(context, (titleList as ArrayList<String>)[groupPosition] + " List Expanded.", Toast.LENGTH_SHORT).show()
//    }
//
//    override fun onGroupCollapsed(groupPosition: Int) {
//        Toast.makeText(context, (titleList as ArrayList<String>)[groupPosition] + " List Collapsed.", Toast.LENGTH_SHORT).show()
//    }

    override fun isChildSelectable(listPosition: Int, expandedListPosition: Int): Boolean {
        return true
    }
}
