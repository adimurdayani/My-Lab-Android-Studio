package com.akbar.laboratoriumapp.ui.profil.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.akbar.laboratoriumapp.ui.profil.edit.editprofil.FragmentEditProfile
import com.akbar.laboratoriumapp.ui.profil.edit.edtipassword.FragmentEditPassword

class AdapterTabEdit(var context: Context, fm: FragmentManager, var totalTabs: Int) :
    FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                FragmentEditProfile()
            }
            1 -> {
                FragmentEditPassword()
            }
            else -> getItem(position)
        }

    }

    override fun getCount(): Int {
        return totalTabs
    }
}