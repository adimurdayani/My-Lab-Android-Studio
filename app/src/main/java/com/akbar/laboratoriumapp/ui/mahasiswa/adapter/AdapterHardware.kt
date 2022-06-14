package com.akbar.laboratoriumapp.ui.mahasiswa.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.akbar.laboratoriumapp.R
import com.akbar.laboratoriumapp.core.model.Pendaftaran
import com.akbar.laboratoriumapp.ui.mahasiswa.detail.DetailHardwareActivity
import com.google.gson.Gson
import java.util.*
import kotlin.collections.ArrayList

class AdapterHardware(val activity: Activity, private val data: ArrayList<Pendaftaran>) :
    RecyclerView.Adapter<AdapterHardware.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tv_nama = view.findViewById<TextView>(R.id.tv_nama)
        val tv_nim = view.findViewById<TextView>(R.id.tv_nim)
        val tv_semester = view.findViewById<TextView>(R.id.tv_semester)
        val btn_detail = view.findViewById<LinearLayout>(R.id.btn_detail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.list_mahasiswa, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tv_nama.text = data[position].nama
        holder.tv_nim.text = data[position].nim
        holder.tv_semester.text = data[position].semester
        holder.btn_detail.setOnClickListener {
            val json = Gson().toJson(data[position], Pendaftaran::class.java)
            val i = Intent(activity, DetailHardwareActivity::class.java)
            i.putExtra("extra", json)
            activity.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    private var searchData: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val searchList: ArrayList<Pendaftaran> = ArrayList<Pendaftaran>()
            if (constraint.toString().isEmpty()) {
                searchList.addAll(data)
            } else {
                for (getRekamMedik in data) {
                    if (getRekamMedik.nama.toLowerCase(Locale.ROOT)
                            .contains(constraint.toString().toLowerCase(Locale.ROOT)) ||
                        getRekamMedik.nim.toLowerCase(Locale.ROOT)
                            .contains(constraint.toString().toLowerCase(Locale.ROOT))
                    ) {
                        searchList.add(getRekamMedik)
                    }
                }
            }
            val results = FilterResults()
            results.values = searchList
            return results
        }

        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            data.clear()
            data.addAll(results.values as Collection<Pendaftaran>)
            notifyDataSetChanged()
        }
    }

    fun getSearchData(): Filter {
        return searchData
    }
}