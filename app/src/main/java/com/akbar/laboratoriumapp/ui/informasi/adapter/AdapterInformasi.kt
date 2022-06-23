package com.akbar.laboratoriumapp.ui.informasi.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.akbar.laboratoriumapp.R
import com.akbar.laboratoriumapp.core.model.Informasi
import java.util.*
import kotlin.collections.ArrayList

class AdapterInformasi(val activity: Activity, private val data: ArrayList<Informasi>) :
    RecyclerView.Adapter<AdapterInformasi.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tv_keternagan = view.findViewById<TextView>(R.id.tv_keternagan)
        val tv_tanggal_tutup = view.findViewById<TextView>(R.id.tv_tanggal_tutup)
        val tv_tanggal_buka = view.findViewById<TextView>(R.id.tv_tanggal_buka)
        val tv_tanggal_sekarang = view.findViewById<TextView>(R.id.tv_tanggal_sekarang)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.list_informasi, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tv_keternagan.text = data[position].keterangan
        holder.tv_tanggal_buka.text =  data[position].tanggal_buka
        holder.tv_tanggal_tutup.text = data[position].tanggal_tutup
        holder.tv_tanggal_sekarang.text = data[position].tanggal_buka
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun getCurrentDate(): String? {
        val c = Calendar.getInstance()
        val year: Int = c[Calendar.YEAR]
        val month: Int = c[Calendar.MONTH]
        val day: Int = c[Calendar.DATE]
        return day.toString() + "/" + (month + 1) + "/" + year
    }

    private var searchData: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val searchList: ArrayList<Informasi> = ArrayList()
            if (constraint.toString().isEmpty()) {
                searchList.addAll(data)
            } else {
                for (getniali in data) {
                    if (getniali.tanggal_buka.toLowerCase(Locale.ROOT)
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
            data.addAll(results.values as Collection<Informasi>)
            notifyDataSetChanged()
        }
    }

    fun getSearchData(): Filter {
        return searchData
    }
}