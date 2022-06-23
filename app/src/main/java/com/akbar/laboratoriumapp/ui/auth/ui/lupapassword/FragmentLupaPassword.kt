package com.akbar.laboratoriumapp.ui.auth.ui.lupapassword

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.akbar.laboratoriumapp.HomeActivity
import com.akbar.laboratoriumapp.R
import com.akbar.laboratoriumapp.core.model.User
import com.akbar.laboratoriumapp.core.remote.network.ApiConfig
import com.akbar.laboratoriumapp.core.remote.response.*
import com.akbar.laboratoriumapp.databinding.FragmentLoginBinding
import com.akbar.laboratoriumapp.databinding.FragmentLupaPasswordBinding
import com.akbar.laboratoriumapp.ui.auth.ui.register.FragmentRegister
import com.akbar.laboratoriumapp.ui.auth.ui.utama.FragmentUtama
import com.akbar.laboratoriumapp.util.SharedPref
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentLupaPassword : Fragment() {

    private var _binding: FragmentLupaPasswordBinding? = null
    private val binding get() = _binding!!
    private lateinit var s: SharedPref

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLupaPasswordBinding.inflate(inflater, container, false)
        val root: View = binding.root
        s = SharedPref(requireActivity())
        setButton()
        return root
    }

    private fun setButton() {

        binding.btnKembali.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frm_login, FragmentUtama())
            transaction.commit()
        }

        binding.btnKirim.setOnClickListener {
            if (validasi()) {
                inputEmail()
            }
        }
    }

    private fun inputEmail() {
        binding.progresBar.visibility = View.VISIBLE
        ApiConfig.instanceRetrofit.getEmail(
            binding.edEmail.text.toString()
        ).enqueue(object : Callback<ResponseModel> {
            override fun onResponse(
                call: Call<ResponseModel>,
                response: Response<ResponseModel>
            ) {
                binding.progresBar.visibility = View.GONE
                if (response.isSuccessful) {
                    val respon = response.body()!!
                    showSuccess("Email ditemukan!")
                    var user = respon.data_register
                    val json = Gson().toJson(user, User::class.java)
                    Log.d("Response", "Message: $json")
                    val intent = Intent(requireActivity(), UbahPasswordUserActivity::class.java)
                    intent.putExtra("extra", json)
                    startActivity(intent)
                } else {
                    binding.progresBar.visibility = View.GONE
                    showError(response.getErrorBody()?.message ?: "Error default")
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                binding.progresBar.visibility = View.GONE
                showError("Terjadi masalah jaringan!")
            }
        })
    }

    private fun validasi(): Boolean {

        binding.apply {

            if (edEmail.text.toString().isEmpty()) {
                edEmail.error = "Kolom email tidak boleh kosong!"
                edEmail.requestFocus()
                return false
            } else if (!Patterns.EMAIL_ADDRESS.matcher(edEmail.text.toString()).matches()) {
                edEmail.error = "Format email salah!. Contoh: gunakan @example.com"
                edEmail.requestFocus()
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