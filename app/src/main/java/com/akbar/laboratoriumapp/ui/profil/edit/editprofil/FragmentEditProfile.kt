package com.akbar.laboratoriumapp.ui.profil.edit.editprofil

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.akbar.laboratoriumapp.R
import com.akbar.laboratoriumapp.core.remote.network.ApiConfig
import com.akbar.laboratoriumapp.core.remote.response.*
import com.akbar.laboratoriumapp.databinding.FragmentEditprofilBinding
import com.akbar.laboratoriumapp.ui.auth.AuthActivity
import com.akbar.laboratoriumapp.util.SharedPref
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentEditProfile : Fragment() {

    private var _binding: FragmentEditprofilBinding? = null
    private val binding get() = _binding!!

    private lateinit var s: SharedPref
    var setKelamin = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditprofilBinding.inflate(inflater, container, false)
        val root: View = binding.root
        s = SharedPref(requireActivity())
        setDisplay()
        setButton()
        setSpinner()
        return root
    }

    private fun setDisplay() {
        val user = s.getUser()!!
        binding.apply {
            edNama.setText(user.nama)
            edEmail.setText(user.email)
            edNim.setText(user.nim)
            edPhone.setText(user.phone)
        }
    }

    private fun setSpinner() {
        val arraykelamin = ArrayList<String>()
        arraykelamin.add("Pilih jenis kelamin")
        arraykelamin.add("Perempuan")
        arraykelamin.add("Laki-Laki")
        val adapter = ArrayAdapter<Any>(
            requireActivity(), R.layout.item_spinner, arraykelamin.toTypedArray()
        )

        binding.spKelamin.adapter = adapter

        binding.spKelamin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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
    }

    private fun setButton() {
        binding.btnUpdate.setOnClickListener {
            if (validasi()) {
                updateData()
            }
        }
    }

    private fun updateData() {
        val user = s.getUser()!!
        binding.apply {
            progreButton.visibility = View.VISIBLE
            tvUpdate.visibility = View.GONE
            ApiConfig.instanceRetrofit.edit_profil(
                user.id,
                edNim.text.toString(),
                edNama.text.toString(),
                edEmail.text.toString(),
                edPhone.text.toString(),
                setKelamin
            )
                .enqueue(object :
                    Callback<ResponseModel> {
                    override fun onResponse(
                        call: Call<ResponseModel>,
                        response: Response<ResponseModel>
                    ) {
                        if (response.isSuccessful) {
                            progreButton.visibility = View.GONE
                            tvUpdate.visibility = View.VISIBLE
                            showSuccess("Profil berhasil diupdate!")
                            s.setStatusLogin(false)
                            pushAcitivty(AuthActivity::class.java)
                        } else {
                            progreButton.visibility = View.GONE
                            tvUpdate.visibility = View.VISIBLE
                            showError(response.getErrorBody()?.message ?: "Error default")
                        }
                    }

                    override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                        progreButton.visibility = View.GONE
                        tvUpdate.visibility = View.VISIBLE
                        showError("Terjadi masalah jaringan")
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
            if (edEmail.text.toString().isEmpty()) {
                edEmail.error = "Kolom email tidak boleh kosong!"
                edEmail.requestFocus()
                return false
            } else if (!Patterns.EMAIL_ADDRESS.matcher(binding.edEmail.text.toString()).matches()) {
                binding.edEmail.error = "Format email salah!. Contoh: gunakan @example.com"
                binding.edEmail.requestFocus()
                return false
            }
            if (edPhone.text.toString().isEmpty()) {
                edPhone.error = "Kolom phone tidak boleh kosong!"
                edPhone.requestFocus()
                return false
            }
            return true
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}