package com.akbar.laboratoriumapp.ui.sertifikat.adapter

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
import com.akbar.laboratoriumapp.core.model.Nilai
import com.akbar.laboratoriumapp.ui.sertifikat.detail.DetailNilaiSertifikatSoftwareActivity
import com.google.gson.Gson
import java.util.*
import kotlin.collections.ArrayList

class AdapterSertifikatSoftware(val activity: Activity, private val data: ArrayList<Nilai>) :
    RecyclerView.Adapter<AdapterSertifikatSoftware.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tv_nama = view.findViewById<TextView>(R.id.tv_nama)
        val tv_nim = view.findViewById<TextView>(R.id.tv_nim)
        val btn_detail = view.findViewById<LinearLayout>(R.id.btn_detail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.list_sertifikat, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tv_nama.text = data[position].nama
        holder.tv_nim.text = data[position].nim
        holder.btn_detail.setOnClickListener {
            val json = Gson().toJson(data[position], Nilai::class.java)
            val i = Intent(activity, DetailNilaiSertifikatSoftwareActivity::class.java)
            i.putExtra("extra", json)
            activity.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    private var searchData: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val searchList: ArrayList<Nilai> = ArrayList<Nilai>()
            if (constraint.toString().isEmpty()) {
                searchList.addAll(data)
            } else {
                for (getniali in data) {
                    if (getniali.nama.toLowerCase(Locale.ROOT)
                            .contains(constraint.toString().toLowerCase(Locale.ROOT)) ||
                        getniali.nim.toLowerCase(Locale.ROOT)
                            .contains(constraint.toString().toLowerCase(Locale.ROOT))
                    ) {
                        searchList.add(getniali)
                    }
                }
            }
            val results = FilterResults()
            results.values = searchList
            return results
        }

        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            data.clear()
            data.addAll(results.values as Collection<Nilai>)
            notifyDataSetChanged()
        }
    }

    fun getSearchData(): Filter {
        return searchData
    }
}