package com.akbar.laboratoriumapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.akbar.laboratoriumapp.databinding.ActivityMainBinding
import com.akbar.laboratoriumapp.ui.auth.AuthActivity
import com.akbar.laboratoriumapp.util.SharedPref

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    lateinit var s : SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        s = SharedPref(this)

        val handler = Handler()
        handler.postDelayed({
            if (s.getStatusLogin()) {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, AuthActivity::class.java))
                finish()
            }
        }, 3000)
    }
}