package com.akbar.laboratoriumapp.ui.mahasiswa.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.akbar.laboratoriumapp.databinding.FragmentDataHardwareBinding
import com.akbar.laboratoriumapp.ui.mahasiswa.hardware.FragmentHardware
import com.akbar.laboratoriumapp.ui.mahasiswa.software.FragmentSoftware
import com.akbar.laboratoriumapp.ui.nilai.hardware.FragmentNilaiHardware
import com.akbar.laboratoriumapp.ui.nilai.software.FragmentNilaiSoftware
import com.akbar.laboratoriumapp.ui.profil.edit.editprofil.FragmentEditProfile
import com.akbar.laboratoriumapp.ui.profil.edit.edtipassword.FragmentEditPassword

class AdapterTabData(var context: Context, fm: FragmentManager, var totalTabs: Int) :
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