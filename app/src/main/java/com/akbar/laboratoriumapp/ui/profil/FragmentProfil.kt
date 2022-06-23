package com.akbar.laboratoriumapp.ui.profil

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.akbar.laboratoriumapp.core.remote.response.logoutAlert
import com.akbar.laboratoriumapp.databinding.FragmentProfilBinding
import com.akbar.laboratoriumapp.ui.auth.AuthActivity
import com.akbar.laboratoriumapp.ui.mahasiswa.MahasiswaActivity
import com.akbar.laboratoriumapp.ui.nilai.NilaiActivity
import com.akbar.laboratoriumapp.ui.profil.detail.DetailProfilActivity
import com.akbar.laboratoriumapp.ui.profil.edit.EditProfilActivity
import com.akbar.laboratoriumapp.ui.profil.eksport.EksportActivity
import com.akbar.laboratoriumapp.ui.profil.nilai.ProfileNilaiActivity
import com.akbar.laboratoriumapp.ui.sertifikat.SertifikatActivity
import com.akbar.laboratoriumapp.ui.tentang.TentangActivity
import com.akbar.laboratoriumapp.util.SharedPref

class FragmentProfil : Fragment() {

    private var _binding: FragmentProfilBinding? = null
    private val binding get() = _binding!!
    private lateinit var s: SharedPref

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfilBinding.inflate(inflater, container, false)
        val root: View = binding.root
        s = SharedPref(requireActivity())
        setButton()
        return root
    }

    private fun setButton() {
        val user = s.getUser()!!
        binding.apply {
            btnEdit.setOnClickListener {
                val i = Intent(requireActivity(), EditProfilActivity::class.java)
                startActivity(i)
            }
            btnLogout.setOnClickListener {
                logoutAlert(AuthActivity::class.java)
            }
            btnProfile.setOnClickListener {
                val i = Intent(requireActivity(), DetailProfilActivity::class.java)
                startActivity(i)
            }
            btnTentang.setOnClickListener {
                val i = Intent(requireActivity(), TentangActivity::class.java)
                startActivity(i)
            }

            btnDaftarnilaihardware.setOnClickListener {
                val i = Intent(requireActivity(), NilaiActivity::class.java)
                startActivity(i)
            }

            btnDaftarnilaisoftware.setOnClickListener {
                val i = Intent(requireActivity(), NilaiActivity::class.java)
                startActivity(i)
            }

            btnSertifikat.setOnClickListener {
                val i = Intent(requireContext(), EksportActivity::class.java)
                startActivity(i)
            }

            btnLihatnilai.setOnClickListener {
                val i = Intent(requireActivity(), ProfileNilaiActivity::class.java)
                startActivity(i)
            }

            tvNama.text = user.nama
            tvPhone.text = user.phone
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}