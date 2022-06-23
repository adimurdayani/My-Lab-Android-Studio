package com.akbar.laboratoriumapp.ui.sertifikat.detail

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.akbar.laboratoriumapp.core.model.Nilai
import com.akbar.laboratoriumapp.databinding.ActivityDetailNilaiSertifikatBinding
import com.akbar.laboratoriumapp.databinding.ActivityDetailNilaiSertifikatHardwareBinding
import com.google.gson.Gson

class DetailNilaiSertifikatHardwareActivity : AppCompatActivity() {
    private var _binding: ActivityDetailNilaiSertifikatHardwareBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailNilaiSertifikatHardwareBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setButton()
        setDisplay()
    }

    private fun setButton() {
        val json = intent.getStringExtra("extra")
        val data = Gson().fromJson(json, Nilai::class.java)
        binding.btnKembali.setOnClickListener {
            onBackPressed()
        }
        binding.btnDownload.setOnClickListener {
            val uri: Uri =
                Uri.parse("http://192.168.12.35/mylab/sertifikat/cetak_sertifikat_hardware/" + data.nim)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }

    private fun setDisplay() {
        val json = intent.getStringExtra("extra")
        val data = Gson().fromJson(json, Nilai::class.java)
        binding.apply {
            tvNama.text = data.nama
            tvJudul.text = data.nama
            tvNim.text = data.nim
            tvKelamin.text = data.kelamin
            tvLaboratoriuum.text = data.kategori
            tvNilaiPraktikum.text = data.nilai_tugas
        }
    }
}