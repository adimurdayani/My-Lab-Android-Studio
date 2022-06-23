package com.akbar.laboratoriumapp.ui.beranda

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.akbar.laboratoriumapp.R
import com.akbar.laboratoriumapp.core.model.Informasi
import com.akbar.laboratoriumapp.databinding.FragmentHomeBinding
import com.akbar.laboratoriumapp.ui.auth.ui.utama.FragmentUtama
import com.akbar.laboratoriumapp.ui.informasi.InformasiActivity
import com.akbar.laboratoriumapp.ui.mahasiswa.DataLabActivity
import com.akbar.laboratoriumapp.ui.mahasiswa.MahasiswaActivity
import com.akbar.laboratoriumapp.ui.nilai.NilaiActivity
import com.akbar.laboratoriumapp.ui.notifikasi.FragmentNotifikasi
import com.akbar.laboratoriumapp.ui.pendaftaran.PendaftaranActivity
import com.akbar.laboratoriumapp.ui.sertifikat.SertifikatActivity
import com.akbar.laboratoriumapp.util.SharedPref
import es.dmoral.toasty.Toasty

class FragmentHome : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var s: SharedPref

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        s = SharedPref(requireActivity())
        setButton()
        return root
    }

    private fun setButton() {
        val user = s.getUser()!!
        binding.apply {
            btnSoftware.setOnClickListener {
                val i = Intent(requireContext(), DataLabActivity::class.java)
                startActivity(i)
            }
            btnDaftar.setOnClickListener {
                val i = Intent(requireContext(), PendaftaranActivity::class.java)
                startActivity(i)
            }
            btnNilai.setOnClickListener {
                val i = Intent(requireContext(), NilaiActivity::class.java)
                startActivity(i)
            }
            btnInformasi.setOnClickListener {
                val i = Intent(requireContext(), InformasiActivity::class.java)
                startActivity(i)
            }
            btnSertifikat.setOnClickListener {
                val i = Intent(requireContext(), SertifikatActivity::class.java)
                startActivity(i)
            }
            btnNotifikasi.setOnClickListener {
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.frm_home, FragmentNotifikasi())
                transaction.commit()
            }
            btnMahasiswa.setOnClickListener {
                val i = Intent(requireContext(), MahasiswaActivity::class.java)
                startActivity(i)
            }
            tvNama.text = user.nama
        }
    }

    companion object {
        fun newInstance(): FragmentHome {
            val fragment = FragmentHome()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}