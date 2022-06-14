package com.akbar.laboratoriumapp.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.akbar.laboratoriumapp.R
import com.akbar.laboratoriumapp.databinding.ActivityAuthBinding
import com.akbar.laboratoriumapp.ui.auth.ui.utama.FragmentUtama

class AuthActivity : AppCompatActivity() {

    private var _binding: ActivityAuthBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().apply {
            replace(binding.frmLogin.id, FragmentUtama())
            addToBackStack(null)
            commit()
        }
    }
}