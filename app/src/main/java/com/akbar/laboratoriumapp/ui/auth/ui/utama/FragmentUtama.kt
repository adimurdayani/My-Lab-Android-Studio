package com.akbar.laboratoriumapp.ui.auth.ui.utama

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.akbar.laboratoriumapp.R
import com.akbar.laboratoriumapp.databinding.FragmentUtamaBinding
import com.akbar.laboratoriumapp.ui.auth.ui.login.FragmentLogin
import com.akbar.laboratoriumapp.ui.auth.ui.register.FragmentRegister

class FragmentUtama : Fragment() {

    private var _binding: FragmentUtamaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUtamaBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setDisplay()
        return root
    }

    private fun setDisplay() {
        binding.btnMasuk.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frm_login, FragmentLogin())
            transaction.commit()
        }

        binding.btnBuatakun.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frm_login, FragmentRegister())
            transaction.commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}