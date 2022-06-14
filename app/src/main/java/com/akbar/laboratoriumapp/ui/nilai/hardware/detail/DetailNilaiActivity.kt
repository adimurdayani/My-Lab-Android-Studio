package com.akbar.laboratoriumapp.ui.nilai.hardware.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.akbar.laboratoriumapp.core.model.Nilai
import com.akbar.laboratoriumapp.core.model.Pendaftaran
import com.akbar.laboratoriumapp.databinding.ActivityDetailNilaiBinding
import com.akbar.laboratoriumapp.databinding.ActivityNilaiBinding
import com.google.gson.Gson

class DetailNilaiActivity : AppCompatActivity() {

    private var _binding: ActivityDetailNilaiBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailNilaiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setButton()
        setDisplay()
    }

    private fun setDisplay() {
        val json = intent.getStringExtra("extra")
        val data = Gson().fromJson(json, Nilai::class.java)
        binding.apply {
            tvNama.text = data.nama
            tvJudul.text= data.nama
            tvNim.text= data.nim
            tvKelamin.text= data.kelamin
            tvLaboratoriuum.text= data.kategori
            tvNilaiPraktikum.text= data.nilai_tugas
        }
    }

    private fun setButton() {
        binding.btnKembali.setOnClickListener {
            onBackPressed()
        }
    }
}