package com.akbar.laboratoriumapp.ui.jadwal.adapter

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
import com.akbar.laboratoriumapp.core.model.Jadwal
import com.akbar.laboratoriumapp.core.model.Nilai
import com.akbar.laboratoriumapp.core.model.Pendaftaran
import com.akbar.laboratoriumapp.ui.nilai.hardware.detail.DetailNilaiActivity
import com.google.gson.Gson
import java.util.*
import kotlin.collections.ArrayList

class AdapterJadwal(val activity: Activity, private val data: ArrayList<Jadwal>) :
    RecyclerView.Adapter<AdapterJadwal.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tv_praktikum = view.findViewById<TextView>(R.id.tv_praktikum)
        val tv_tanggal = view.findViewById<TextView>(R.id.tv_tanggal)
        val tv_jam = view.findViewById<TextView>(R.id.tv_jam)
        val tv_asisten = view.findViewById<TextView>(R.id.tv_asisten)
        val tv_keterangan = view.findViewById<TextView>(R.id.tv_keterangan)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.list_jadwal, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tv_praktikum.text = data[position].judul
        holder.tv_tanggal.text = data[position].tanggal
        holder.tv_jam.text = data[position].jam
        holder.tv_asisten.text = data[position].nama_dosen
        holder.tv_keterangan.text = data[position].keterangan
    }

    override fun getItemCount(): Int {
        return data.size
    }

    private var searchData: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val searchList: ArrayList<Jadwal> = ArrayList<Jadwal>()
            if (constraint.toString().isEmpty()) {
                searchList.addAll(data)
            } else {
                for (getniali in data) {
                    if (getniali.judul.toLowerCase(Locale.ROOT)
                            .contains(constraint.toString().toLowerCase(Locale.ROOT)) ||
                        getniali.tanggal.toLowerCase(Locale.ROOT)
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
            data.addAll(results.values as Collection<Jadwal>)
            notifyDataSetChanged()
        }
    }

    fun getSearchData(): Filter {
        return searchData
    }
}