package com.akbar.laboratoriumapp.ui.profil.nilai

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.akbar.laboratoriumapp.databinding.ActivityNilaiBinding
import com.akbar.laboratoriumapp.ui.nilai.adapter.AdapterTabNilai
import com.akbar.laboratoriumapp.ui.profil.nilai.adapter.AdapterTabNilaiProfil
import com.akbar.laboratoriumapp.util.SharedPref
import com.google.android.material.tabs.TabLayout

class ProfileNilaiActivity : AppCompatActivity() {

    private var _binding: ActivityNilaiBinding? = null
    private val binding get() = _binding!!
    lateinit var s: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityNilaiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        s = SharedPref(this)
        binding.tvJudul.text = s.getUser()!!.nama
        setTabLayout()
        setButton()
    }

    private fun setButton() {
        binding.btnKembali.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setTabLayout() {
        binding.apply {
            tablayout.addTab(tablayout.newTab().setText("Nilai Hardware"))
            tablayout.addTab(tablayout.newTab().setText("Nilai Software"))
            tablayout.tabGravity = TabLayout.GRAVITY_FILL

            val adapter = AdapterTabNilaiProfil(
                this@ProfileNilaiActivity,
                supportFragmentManager,
                tablayout.tabCount
            )
            viewPager.adapter = adapter
            viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tablayout))
            tablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    viewPager.currentItem = tab!!.position
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {

                }

            })
        }
    }
}