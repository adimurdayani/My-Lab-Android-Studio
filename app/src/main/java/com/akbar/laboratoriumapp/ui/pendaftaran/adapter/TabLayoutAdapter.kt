package com.akbar.laboratoriumapp.ui.pendaftaran.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.akbar.laboratoriumapp.ui.pendaftaran.hardware.FragmentHardware
import com.akbar.laboratoriumapp.ui.pendaftaran.software.FragmentSoftware

internal class TabLayoutAdapter(var context: Context, fm: FragmentManager, var totalTabs: Int) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                FragmentHardware()
            }
            1 -> {
                FragmentSoftware()
            }
            else -> getItem(position)
        }

    }

    override fun getCount(): Int {
        return totalTabs
    }
}