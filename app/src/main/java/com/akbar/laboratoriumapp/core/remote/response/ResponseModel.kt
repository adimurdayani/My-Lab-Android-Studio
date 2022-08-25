package com.akbar.laboratoriumapp.core.remote.response

import com.akbar.laboratoriumapp.core.model.*

class ResponseModel {

    var hardware: ArrayList<Pendaftaran> = ArrayList()
    var software: ArrayList<Pendaftaran> = ArrayList()
    var kategori_register: ArrayList<Lab> = ArrayList()
    val kategori_praktikum: ArrayList<Praktikum> = ArrayList()
    val nilai: ArrayList<Nilai> = ArrayList()
    val jadwal: ArrayList<Jadwal> = ArrayList()
    val mahasiswa: ArrayList<Mahasiswa> = ArrayList()
    val informasi: ArrayList<Informasi> = ArrayList()

    val data_register = User()
    var data = User()
    var pendaftaran = Pendaftaran()
    var foto = Pendaftaran()
}