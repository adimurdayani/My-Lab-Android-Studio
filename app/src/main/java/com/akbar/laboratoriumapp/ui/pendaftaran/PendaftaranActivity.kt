package com.akbar.laboratoriumapp.ui.pendaftaran

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.akbar.laboratoriumapp.databinding.ActivityPendaftaranBinding
import com.akbar.laboratoriumapp.ui.pendaftaran.adapter.TabLayoutAdapter
import com.google.android.material.tabs.TabLayout

class PendaftaranActivity : AppCompatActivity() {

    private var _binding: ActivityPendaftaranBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPendaftaranBinding.inflate(layoutInflater)
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
            tablayout.addTab(tablayout.newTab().setText("Hardware"))
            tablayout.addTab(tablayout.newTab().setText("Software"))
            tablayout.tabGravity = TabLayout.GRAVITY_FILL

            val adapter = TabLayoutAdapter(
                this@PendaftaranActivity,
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