package com.akbar.laboratoriumapp.ui.profil.eksport

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.akbar.laboratoriumapp.databinding.ActivityAuthBinding
import com.akbar.laboratoriumapp.databinding.ActivityEksportBinding
import com.akbar.laboratoriumapp.util.SharedPref

class EksportActivity : AppCompatActivity() {
    private var _binding: ActivityEksportBinding? = null
    private val binding get() = _binding!!
    lateinit var s: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityEksportBinding.inflate(layoutInflater)
        setContentView(binding.root)
        s = SharedPref(this)

        binding.btnKembali.setOnClickListener {
            onBackPressed()
        }

        binding.btnDownloadHardware.setOnClickListener {
            val uri: Uri =
                Uri.parse("http://192.168.12.35/mylab/sertifikat/cetak_sertifikat_hardware/" + s.getUser()!!.nim)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            Log.d("Responser", "message" + s.getUser()!!.nim)
            startActivity(intent)
        }
        binding.btnDownloadSoftware.setOnClickListener {
            val uri: Uri =
                Uri.parse("http://192.168.12.35/mylab/sertifikat/cetak_sertifikat_software/" + s.getUser()!!.nim)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }

}