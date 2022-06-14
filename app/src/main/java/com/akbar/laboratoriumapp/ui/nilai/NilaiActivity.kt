package com.akbar.laboratoriumapp.ui.nilai

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.akbar.laboratoriumapp.databinding.ActivityNilaiBinding
import com.akbar.laboratoriumapp.ui.nilai.adapter.AdapterTabNilai
import com.google.android.material.tabs.TabLayout

class NilaiActivity : AppCompatActivity() {

    private var _binding: ActivityNilaiBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityNilaiBinding.inflate(layoutInflater)
        setContentView(binding.root)
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

            val adapter = AdapterTabNilai(
                this@NilaiActivity,
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