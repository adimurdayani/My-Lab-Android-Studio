package com.akbar.laboratoriumapp.ui.notifikasi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.akbar.laboratoriumapp.databinding.FragmentNotifikasiBinding

class FragmentNotifikasi : Fragment() {

    private var _binding: FragmentNotifikasiBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotifikasiBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

}