package com.akbar.laboratoriumapp.ui.profil.nilai.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.akbar.laboratoriumapp.core.model.Nilai
import com.akbar.laboratoriumapp.databinding.ActivityDetailNilaiProfileBinding
import com.akbar.laboratoriumapp.databinding.ActivityNilaiBinding
import com.google.gson.Gson

class DetailNilaiProfileActivity : AppCompatActivity() {
    private var _binding: ActivityDetailNilaiProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailNilaiProfileBinding.inflate(layoutInflater)
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