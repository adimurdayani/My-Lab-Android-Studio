package com.akbar.laboratoriumapp.ui.tentang.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.akbar.laboratoriumapp.R

class AdapterListView(
    var context: Context,
    var header: MutableList<String>,
    var body: MutableList<MutableList<String>>
) : BaseExpandableListAdapter() {

    override fun getGroupCount(): Int {
        return header.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return body[groupPosition].size
    }

    override fun getGroup(groupPosition: Int): Any {
        return header[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return body[groupPosition][childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var view = convertView
        if (view == null) {
            val inflate =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflate.inflate(R.layout.parent_item, null)
        }
        val title = view!!.findViewById<TextView>(R.id.parent)
        title.text = getGroup(groupPosition).toString()
        return view
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var view = convertView
        if (view == null) {
            val inflate =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflate.inflate(R.layout.child_item, null)
        }
        val title = view!!.findViewById<TextView>(R.id.child)
        title.text = getChild(groupPosition, childPosition).toString()
        return view
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}