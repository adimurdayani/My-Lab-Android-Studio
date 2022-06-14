package com.akbar.laboratoriumapp.ui.mahasiswa.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.akbar.laboratoriumapp.core.model.Pendaftaran
import com.akbar.laboratoriumapp.databinding.ActivityDetailHardwareBinding
import com.google.gson.Gson

class DetailHardwareActivity : AppCompatActivity() {
    private var _binding: ActivityDetailHardwareBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailHardwareBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setButton()
        setDisplay()
    }

    private fun setDisplay() {
        val json = intent.getStringExtra("extra");
        val data = Gson().fromJson(json, Pendaftaran::class.java)

        binding.apply {
            tvNama.text = data.nama
            tvNim.text = data.nim
            tvKelamin.text = data.kelamin
            tvSemester.text = data.semester
            tvAgama.text = data.agama
            tvAlamat.text = data.alamat
            tvNamaWali.text = data.nama_ortu
            tvAlamatWali.text = data.alamat_ortu
            tvPekerjaanWali.text = data.pekerjaan_ortu
            tvJudul.text = data.nama
        }
    }

    private fun setButton() {
        binding.btnKembali.setOnClickListener {
            onBackPressed()
        }
    }
}