package com.akbar.laboratoriumapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.akbar.laboratoriumapp.databinding.ActivityHomeBinding
import com.akbar.laboratoriumapp.databinding.ActivityMainBinding
import com.akbar.laboratoriumapp.ui.beranda.FragmentHome
import com.akbar.laboratoriumapp.ui.jadwal.FragmentJadwal
import com.akbar.laboratoriumapp.ui.notifikasi.FragmentNotifikasi
import com.akbar.laboratoriumapp.ui.profil.FragmentProfil
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    private var _binding: ActivityHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.navigasi.setOnNavigationItemSelectedListener(navigationItemView)
        val fragment = FragmentHome.newInstance()
        addFragment(fragment)

    }

    private val navigationItemView = BottomNavigationView.OnNavigationItemSelectedListener {
        when (it.itemId) {
            R.id.navigation_home -> {
                val fragment = FragmentHome.newInstance()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_jadwal -> {
                val fragment = FragmentJadwal()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_notifikasi -> {
                val fragment = FragmentNotifikasi()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profil -> {
                val fragment = FragmentProfil()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.side_in, R.anim.side_out)
            .replace(R.id.frm_home, fragment, fragment.javaClass.simpleName)
            .commit()
    }

}