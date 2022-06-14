package com.akbar.laboratoriumapp.ui.tentang

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.akbar.laboratoriumapp.databinding.ActivityTentangBinding
import com.akbar.laboratoriumapp.ui.tentang.adapter.AdapterListView

class TentangActivity : AppCompatActivity() {

    private var _binding: ActivityTentangBinding? = null
    private val binding get() = _binding!!

    private val header: MutableList<String> = ArrayList()
    private val body: MutableList<MutableList<String>> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityTentangBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setButton()
        setListData()
        setAdapter()
    }

    private fun setAdapter() {
        binding.expanList.setAdapter(AdapterListView(this, header, body))
    }

    private fun setListData() {
        val kontent1: MutableList<String> = ArrayList()
        kontent1.add("Aplikasi My Lab adalah aplikasi yang digunakan oleh mahasiswa dan pihak kampus untuk mengelola data-data mahasiswa yang telah melakukan proses pendaftaran di laboratorium IT, serta mahasiswa dapat melihat hasil dari praktikum yang telah dilakukannya.")

        val kontent2: MutableList<String> = ArrayList()
        kontent2.add("Pada saat menggunakan aplikasi My Lab anda harus melakukan proses registrasi akun terlebih dahulu agar dapat melakukan proses login dan mengakses halaman utama.")
        kontent2.add("Setelah anda berhasil melakukan proses pembuatan akun dan login ke aplikasi. Selanjutnya anda bisa melakukan pendaftaran pada Laboratorium hardware dan software agar data diri anda bisa diketahui oleh pihak Laboratorium")
        kontent2.add("Setelah anda berhasil melakukan proses pendaftaran di Laboratorium hardware dan software. Selanjutnya ada bisa melihat nilai dari hasil praktikum yang telah kalian ikuti")
        kontent2.add("Pada aplikasi My Lab anda bisa melihat daftar mahasiswa yang ada pada Laboratorium hardware dan software, serta anda juga bisa melihat daftar nilai dari Laboratorium hardware dan software. Anda juga bisa melihat jadwal praktikum yang ada")
        kontent2.add("Jika anda ingin merubah password dan profil, anda harus masuk ke halaman profil kemudiah klik tombol edit yang ada pada halaman profil. di halaman edit anda bisa melihat tab menu ubah password dan ubah profil")

        val kontent3: MutableList<String> = ArrayList()
        kontent3.add("tentang pembuat")

        header.add("Definisi My Lab")
        header.add("Cara Kerja My Lab")
        header.add("Tentang Pengembang")

        body.add(kontent1)
        body.add(kontent2)
        body.add(kontent3)
    }

    private fun setButton() {
        binding.apply {
            btnKembali.setOnClickListener {
                onBackPressed()
            }
        }
    }
}