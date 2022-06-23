package com.akbar.laboratoriumapp.ui.pendaftaran.hardware

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.akbar.laboratoriumapp.HomeActivity
import com.akbar.laboratoriumapp.R
import com.akbar.laboratoriumapp.core.model.Alamat
import com.akbar.laboratoriumapp.core.model.Lab
import com.akbar.laboratoriumapp.core.model.Praktikum
import com.akbar.laboratoriumapp.core.remote.network.ApiConfig
import com.akbar.laboratoriumapp.core.remote.network.ApiConfigAlamat
import com.akbar.laboratoriumapp.core.remote.response.*
import com.akbar.laboratoriumapp.databinding.FragmentHardwareBinding
import com.akbar.laboratoriumapp.databinding.FragmentLoginBinding
import com.akbar.laboratoriumapp.ui.auth.ui.register.FragmentRegister
import com.akbar.laboratoriumapp.ui.auth.ui.utama.FragmentUtama
import com.akbar.laboratoriumapp.util.ApiKey
import com.akbar.laboratoriumapp.util.SharedPref
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentHardware : Fragment() {

    private var _binding: FragmentHardwareBinding? = null
    private val binding get() = _binding!!

    private lateinit var s: SharedPref
    private var getProv = Alamat()
    private var getKota = Alamat()
    private var getPraktikum = Praktikum()
    private var getLab = Lab()
    var setprovinsi = ""
    var setkota = ""
    var setKelamin = ""
    var setAgama = ""
    var setSemester = ""
    var setPraktikum: Int? = 0
    var setLab: Int? = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHardwareBinding.inflate(inflater, container, false)
        val root: View = binding.root
        s = SharedPref(requireActivity())
        setDisplay()
        setButton()
        setSpinner()
        return root
    }

    fun setDisplay() {
        val user = s.getUser()!!
        binding.apply {
            edNama.setText(user.nama)
            edNim.setText(user.nim)
        }
    }

    private fun setButton() {
        binding.btnDaftar.setOnClickListener {
            if (validasi()) {
                simpan()
            }
        }
    }

    private fun simpan() {
        binding.apply {
            progreButton.visibility = View.VISIBLE
            tvKirim.visibility = View.GONE
            ApiConfig.instanceRetrofit.createPendaftaranHardware(
                edNama.text.toString(),
                edNim.text.toString(),
                setKelamin,
                setAgama,
                setPraktikum,
                setLab,
                setSemester,
                edAlamat.text.toString(),
                edNamaortu.text.toString(),
                edPekerjaanortu.text.toString(),
                edAlamatortu.text.toString(),
                setkota,
                setprovinsi
            )
                .enqueue(object :
                    Callback<ResponseModel> {
                    override fun onResponse(
                        call: Call<ResponseModel>,
                        response: Response<ResponseModel>
                    ) {
                        if (response.isSuccessful) {
                            progreButton.visibility = View.GONE
                            tvKirim.visibility = View.VISIBLE
                            showSuccess("Data berhasil dikirim!")
                        } else {
                            progreButton.visibility = View.GONE
                            tvKirim.visibility = View.VISIBLE
                            showError(response.getErrorBody()?.message ?: "Error default")
                        }
                    }

                    override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                        progreButton.visibility = View.GONE
                        tvKirim.visibility = View.VISIBLE
                        showError("Terjadi masalah jaringan")
                    }

                })
        }
    }

    private fun setSpinner() {
        binding.apply {
            val arraykelamin = ArrayList<String>()
            arraykelamin.add("Pilih jenis kelamin")
            arraykelamin.add("Perempuan")
            arraykelamin.add("Laki-Laki")
            val adapter = ArrayAdapter<Any>(
                requireActivity(), R.layout.item_spinner, arraykelamin.toTypedArray()
            )

            spKelamin.adapter = adapter

            spKelamin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (position != 0) {
                        setKelamin = parent!!.selectedItem.toString()
                        Log.d("Response", "Kelamin: $setKelamin")
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

            }

            val arrayagama = ArrayList<String>()
            arrayagama.add("Pilih agama")
            arrayagama.add("Islam")
            arrayagama.add("Kristen")
            arrayagama.add("Hindu")
            arrayagama.add("Budha")
            val adapterAgama = ArrayAdapter<Any>(
                requireActivity(), R.layout.item_spinner, arrayagama.toTypedArray()
            )

            spAgama.adapter = adapterAgama

            spAgama.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (position != 0) {
                        setAgama = parent!!.selectedItem.toString()
                        Log.d("Response", "Agama: $setAgama")
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

            }

            val arraySemester = ArrayList<String>()
            arraySemester.add("Pilih semester")
            arraySemester.add("Semester I")
            arraySemester.add("Semester II")
            arraySemester.add("Semester III")
            arraySemester.add("Semester IV")
            arraySemester.add("Semester V")
            arraySemester.add("Semester VI")
            arraySemester.add("Semester VII")
            arraySemester.add("Semester VIII")

            val adapterSemester = ArrayAdapter<Any>(
                requireActivity(), R.layout.item_spinner, arraySemester.toTypedArray()
            )

            spSemester.adapter = adapterSemester

            spSemester.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (position != 0) {
                        setSemester = parent!!.selectedItem.toString()
                        Log.d("Response", "Semester: $setSemester")
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

            }
        }
    }

    private fun setProvinsi() {
        binding.apply {
            progressbar.visibility = View.VISIBLE
            ApiConfigAlamat.instanceRetrofit.getProvinsi(ApiKey.key).enqueue(object :
                Callback<ResponseAlamat> {
                override fun onResponse(
                    call: Call<ResponseAlamat>,
                    response: Response<ResponseAlamat>
                ) {
                    if (response.isSuccessful) {
                        progressbar.visibility = View.GONE
                        divProvinsi.visibility = View.VISIBLE

                        val res = response.body()!!

                        val arrayString = ArrayList<String>()
                        val listprovinsi = res.rajaongkir.results
                        arrayString.add("Pilih Provinsi")
                        for (prov in listprovinsi) {
                            arrayString.add(prov.province!!)
                        }

                        val adapter = ArrayAdapter<Any>(
                            requireActivity(), R.layout.item_spinner, arrayString.toTypedArray()
                        )

                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spProvinsi.adapter = adapter
                        spProvinsi.onItemSelectedListener =
                            object : AdapterView.OnItemSelectedListener {
                                override fun onItemSelected(
                                    parent: AdapterView<*>?,
                                    view: View?,
                                    position: Int,
                                    id: Long
                                ) {
                                    if (position != 0) getProv = listprovinsi[position - 1]
                                    val idprov = getProv.province_id
                                    setprovinsi = getProv.province
                                    Log.d("Response", "Provinsi: " + setprovinsi)
                                    setKota(idprov)
                                }

                                override fun onNothingSelected(parent: AdapterView<*>?) {

                                }

                            }
                    }
                }

                override fun onFailure(call: Call<ResponseAlamat>, t: Throwable) {
                    Log.d("RESPONSE", "ERROR: " + t.message)
                }

            })
        }
    }

    private fun setKota(id: String) {
        binding.apply {
            progressbar.visibility = View.VISIBLE
            ApiConfigAlamat.instanceRetrofit.getKota(ApiKey.key, id).enqueue(object :
                Callback<ResponseAlamat> {
                override fun onResponse(
                    call: Call<ResponseAlamat>,
                    response: Response<ResponseAlamat>
                ) {
                    if (response.isSuccessful) {
                        progressbar.visibility = View.GONE

                        val res = response.body()!!

                        val arrayString = ArrayList<String>()
                        val listkota = res.rajaongkir.results
                        arrayString.add("Pilih Kabupaten/Kota")
                        for (kot in listkota) {
                            divKabupaten.visibility = View.VISIBLE
                            arrayString.add(kot.city_name)
                        }

                        val adapter = ArrayAdapter<Any>(
                            requireActivity(), R.layout.item_spinner, arrayString.toTypedArray()
                        )

                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spKabupaten.adapter = adapter
                        spKabupaten.onItemSelectedListener =
                            object : AdapterView.OnItemSelectedListener {
                                override fun onItemSelected(
                                    parent: AdapterView<*>?,
                                    view: View?,
                                    position: Int,
                                    id: Long
                                ) {
                                    if (position != 0) getKota = listkota[position - 1]
                                    setkota = getKota.city_name
                                    Log.d("Response", "Kota: " + setkota)

                                }

                                override fun onNothingSelected(parent: AdapterView<*>?) {

                                }

                            }
                    }
                }

                override fun onFailure(call: Call<ResponseAlamat>, t: Throwable) {
                    Log.d("RESPONSE", "ERROR: " + t.message)
                }

            })
        }
    }

    private fun setPraktikum() {
        binding.apply {
            progresBar2.visibility = View.VISIBLE
            ApiConfig.instanceRetrofit.getpraktikum().enqueue(object :
                Callback<ResponseModel> {
                override fun onResponse(
                    call: Call<ResponseModel>,
                    response: Response<ResponseModel>
                ) {
                    if (response.isSuccessful) {
                        progresBar2.visibility = View.GONE

                        val res = response.body()!!

                        val arrayString = ArrayList<String>()
                        val listpraktikum = res.kategori_praktikum
                        arrayString.add("Pilih Praktikum")
                        for (praktikum in listpraktikum) {
                            divPraktikum.visibility = View.VISIBLE
                            arrayString.add(praktikum.kategori!!)
                        }

                        val adapter = ArrayAdapter<Any>(
                            requireActivity(), R.layout.item_spinner, arrayString.toTypedArray()
                        )

                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spPraktikum.adapter = adapter
                        spPraktikum.onItemSelectedListener =
                            object : AdapterView.OnItemSelectedListener {
                                override fun onItemSelected(
                                    parent: AdapterView<*>?,
                                    view: View?,
                                    position: Int,
                                    id: Long
                                ) {
                                    if (position != 0) getPraktikum = listpraktikum[position - 1]
                                    setPraktikum = getPraktikum.id
                                    Log.d("Response", "Praktikum: " + setPraktikum)

                                }

                                override fun onNothingSelected(parent: AdapterView<*>?) {

                                }

                            }
                    }
                }

                override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                    Log.d("RESPONSE", "ERROR: " + t.message)
                }

            })
        }
    }

    private fun setLab() {
        binding.apply {
            progresBar2.visibility = View.VISIBLE
            ApiConfig.instanceRetrofit.getLab().enqueue(object :
                Callback<ResponseModel> {
                override fun onResponse(
                    call: Call<ResponseModel>,
                    response: Response<ResponseModel>
                ) {
                    if (response.isSuccessful) {
                        progresBar2.visibility = View.GONE

                        val res = response.body()!!

                        val arrayString = ArrayList<String>()
                        val listlab = res.kategori_register
                        arrayString.add("Pilih Laboratorium")
                        for (lab in listlab) {
                            divLab.visibility = View.VISIBLE
                            arrayString.add(lab.kategori)
                        }

                        val adapter = ArrayAdapter<Any>(
                            requireActivity(), R.layout.item_spinner, arrayString.toTypedArray()
                        )

                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spLab.adapter = adapter
                        spLab.onItemSelectedListener =
                            object : AdapterView.OnItemSelectedListener {
                                override fun onItemSelected(
                                    parent: AdapterView<*>?,
                                    view: View?,
                                    position: Int,
                                    id: Long
                                ) {
                                    if (position != 0) getLab = listlab[position - 1]
                                    setLab = getLab.id
                                    Log.d("Response", "Laboratorium: " + setLab)
                                }

                                override fun onNothingSelected(parent: AdapterView<*>?) {

                                }

                            }
                    }
                }

                override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                    Log.d("RESPONSE", "ERROR: " + t.message)
                }

            })
        }
    }

    private fun validasi(): Boolean {

        binding.apply {
            if (edNim.text.toString().isEmpty()) {
                edNim.error = "Kolom NIM tidak boleh kosong!"
                edNim.requestFocus()
                return false
            } else if (binding.edNim.text.toString().length < 13) {
                binding.edNim.error = "NIM tidak boleh kurang dari 13 digit!"
                binding.edNim.requestFocus()
                return false
            }
            if (edNama.text.toString().isEmpty()) {
                edNama.error = "Kolom nama tidak boleh kosong!"
                edNama.requestFocus()
                return false
            }
            if (edAlamat.text.toString().isEmpty()) {
                edAlamat.error = "Kolom alamat tidak boleh kosong!"
                edAlamat.requestFocus()
                return false
            }
            if (edNamaortu.text.toString().isEmpty()) {
                edNamaortu.error = "Kolom nama orang tua tidak boleh kosong!"
                edNamaortu.requestFocus()
                return false
            }
            if (edPekerjaanortu.text.toString().isEmpty()) {
                edPekerjaanortu.error = "Kolom pekerjaan orang tua tidak boleh kosong!"
                edPekerjaanortu.requestFocus()
                return false
            }
            return true
        }

    }

    override fun onResume() {
        super.onResume()
        setProvinsi()
        setPraktikum()
        setLab()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}