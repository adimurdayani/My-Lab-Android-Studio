package com.akbar.laboratoriumapp.ui.profil.nilai.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.akbar.laboratoriumapp.ui.nilai.hardware.FragmentNilaiHardware
import com.akbar.laboratoriumapp.ui.nilai.software.FragmentNilaiSoftware
import com.akbar.laboratoriumapp.ui.profil.edit.editprofil.FragmentEditProfile
import com.akbar.laboratoriumapp.ui.profil.edit.edtipassword.FragmentEditPassword
import com.akbar.laboratoriumapp.ui.profil.nilai.hardware.FragmentNilaiHardwareProfile
import com.akbar.laboratoriumapp.ui.profil.nilai.software.FragmentNilaiSoftwareProfile

class AdapterTabNilaiProfil(var context: Context, fm: FragmentManager, var totalTabs: Int) :
    FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                FragmentNilaiHardwareProfile()
            }
            1 -> {
                FragmentNilaiSoftwareProfile()
            }
            else -> getItem(position)
        }

    }

    override fun getCount(): Int {
        return totalTabs
    }
}