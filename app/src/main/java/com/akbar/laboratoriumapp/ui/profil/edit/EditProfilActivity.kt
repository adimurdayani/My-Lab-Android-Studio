package com.akbar.laboratoriumapp.ui.profil.edit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.akbar.laboratoriumapp.databinding.ActivityEditProfilBinding
import com.akbar.laboratoriumapp.ui.profil.adapter.AdapterTabEdit
import com.google.android.material.tabs.TabLayout

class EditProfilActivity : AppCompatActivity() {

    private var _binding: ActivityEditProfilBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityEditProfilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTabLayout()
        setButton()
    }

    private fun setButton() {
        binding.apply {
            btnKembali.setOnClickListener {
                onBackPressed()
            }
        }
    }

    private fun setTabLayout() {
        binding.apply {
            tablayout.addTab(tablayout.newTab().setText("Ubah Profil"))
            tablayout.addTab(tablayout.newTab().setText("Ubah Password"))
            tablayout.tabGravity = TabLayout.GRAVITY_FILL

            val adapter = AdapterTabEdit(
                this@EditProfilActivity,
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