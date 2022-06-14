package com.akbar.laboratoriumapp.ui.profil.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.akbar.laboratoriumapp.databinding.ActivityDetailProfilBinding
import com.akbar.laboratoriumapp.databinding.ActivityEditProfilBinding
import com.akbar.laboratoriumapp.util.SharedPref

class DetailProfilActivity : AppCompatActivity() {

    private var _binding: ActivityDetailProfilBinding? = null
    private val binding get() = _binding!!
    private lateinit var s: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailProfilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        s = SharedPref(this)
        setButton()
        setDisplay()
    }

    private fun setDisplay() {
        val user = s.getUser()!!
        binding.apply {
            tvNama.text = user.nama
            tvNim.text = user.nim
            tvEmail.text = user.email
            tvPhone.text = user.phone
        }
    }

    private fun setButton() {
        binding.apply {
            btnKembali.setOnClickListener {
                onBackPressed()
            }
        }
    }
}