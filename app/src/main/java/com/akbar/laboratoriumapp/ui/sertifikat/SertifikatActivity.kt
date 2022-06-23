package com.akbar.laboratoriumapp.ui.sertifikat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.akbar.laboratoriumapp.HomeActivity
import com.akbar.laboratoriumapp.databinding.ActivitySertifikatBinding
import com.akbar.laboratoriumapp.ui.nilai.adapter.AdapterTabNilai
import com.akbar.laboratoriumapp.ui.sertifikat.adapter.AdapterTabSertifikat
import com.google.android.material.tabs.TabLayout

class SertifikatActivity : AppCompatActivity() {
    private var _binding: ActivitySertifikatBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySertifikatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setButton()
        setTabLayout()
    }

    private fun setButton() {
        binding.btnKembali.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }

    private fun setTabLayout() {
        binding.apply {
            tablayout.addTab(tablayout.newTab().setText("Nilai Hardware"))
            tablayout.addTab(tablayout.newTab().setText("Nilai Software"))
            tablayout.tabGravity = TabLayout.GRAVITY_FILL

            val adapter = AdapterTabSertifikat(
                this@SertifikatActivity,
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