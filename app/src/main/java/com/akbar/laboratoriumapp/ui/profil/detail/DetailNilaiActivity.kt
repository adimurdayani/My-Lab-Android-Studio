package com.akbar.laboratoriumapp.ui.profil.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.akbar.laboratoriumapp.databinding.ActivityAuthBinding
import com.akbar.laboratoriumapp.databinding.ActivityDetailNilaiBinding

class DetailNilaiActivity : AppCompatActivity() {
    private var _binding: ActivityDetailNilaiBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailNilaiBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}