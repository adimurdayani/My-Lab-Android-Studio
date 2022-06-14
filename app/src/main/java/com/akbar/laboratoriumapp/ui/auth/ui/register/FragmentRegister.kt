package com.akbar.laboratoriumapp.ui.auth.ui.register

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.akbar.laboratoriumapp.R
import com.akbar.laboratoriumapp.core.remote.network.ApiConfig
import com.akbar.laboratoriumapp.core.remote.response.*
import com.akbar.laboratoriumapp.databinding.FragmentRegisterBinding
import com.akbar.laboratoriumapp.ui.auth.ui.login.FragmentLogin
import com.akbar.laboratoriumapp.ui.auth.ui.utama.FragmentUtama
import com.akbar.laboratoriumapp.util.SharedPref
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentRegister : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private lateinit var s: SharedPref

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val root: View = binding.root
        s = SharedPref(requireActivity())
        setButton()
        return root
    }

    private fun setButton() {

        binding.apply {
            btnKembali.setOnClickListener {
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.frm_login, FragmentUtama())
                transaction.commit()
            }
            btnMasuk.setOnClickListener {
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.frm_login, FragmentLogin())
                transaction.commit()
            }
            btnRegister.setOnClickListener {
                if (validasi()) {
                    registerUser()
                }
            }
        }
    }

    private fun registerUser() {
        binding.progressBar.visibility = View.VISIBLE
        ApiConfig.instanceRetrofit.register(
            binding.edNim.text.toString(),
            binding.edNama.text.toString(),
            binding.edEmail.text.toString(),
            binding.edPhone.text.toString(),
            binding.edPassword.text.toString()
        ).enqueue(object : Callback<ResponseModel> {
            override fun onResponse(
                call: Call<ResponseModel>,
                response: Response<ResponseModel>
            ) {
                if (response.isSuccessful) {

                    binding.progressBar.visibility = View.GONE
                    showSuccess("Registrasi akun sukses!. Tunggu konfirmasi aktivasi dari admin")

                } else {
                    binding.progressBar.visibility = View.GONE
                    showError(response.getErrorBody()?.message ?: "Error default")
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                binding.progressBar.visibility = View.VISIBLE
                showError("Terjadi masalah jaringan!")
            }
        })
    }

    private fun validasi(): Boolean {

        if (binding.edNim.text.toString().isEmpty()) {
            binding.edNim.error = "Kolom NIM tidak boleh kosong!"
            binding.edNim.requestFocus()
            return false
        } else if (binding.edNim.text.toString().length < 13) {
            binding.edNim.error = "NIM tidak boleh kurang dari 13 digit!"
            binding.edNim.requestFocus()
            return false
        }
        if (binding.edNama.text.toString().isEmpty()) {
            binding.edNama.error = "Kolom nama tidak boleh kosong!"
            binding.edNama.requestFocus()
            return false
        }
        if (binding.edEmail.text.toString().isEmpty()) {
            binding.edEmail.error = "Kolom email tidak boleh kosong!"
            binding.edEmail.requestFocus()
            return false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(binding.edEmail.text.toString()).matches()) {
            binding.edEmail.error = "Format email salah!. Contoh: gunakan @example.com"
            binding.edEmail.requestFocus()
            return false
        }
        if (binding.edPhone.text.toString().isEmpty()) {
            binding.edPhone.error = "Kolom phone tidak boleh kosong!"
            binding.edPhone.requestFocus()
            return false
        }
        if (binding.edPassword.text.toString().isEmpty()) {
            binding.edPassword.error = "Kolom password tidak boleh kosong!"
            binding.edPassword.requestFocus()
            return false
        } else if (binding.edPassword.text.toString().length < 6) {
            binding.edPassword.error = "Password tidak boleh kurang dari 6 karakter!"
            binding.edPassword.requestFocus()
            return false
        }
        if (binding.edConfpassword.text.toString().isEmpty()) {
            binding.edConfpassword.error = "Kolom konfirmasi password tidak boleh kosong!"
            binding.edConfpassword.requestFocus()
            return false
        } else if (binding.edConfpassword.text.toString().length < 6) {
            binding.edConfpassword.error = "Konfirmasi password tidak boleh kurang dari 6 karakter!"
            binding.edConfpassword.requestFocus()
            return false
        } else if (!binding.edConfpassword.text.toString()
                .matches(binding.edPassword.text.toString().toRegex())
        ) {
            binding.edConfpassword.error = "Konfirmasi password tidak sama dengan password"
            binding.edConfpassword.requestFocus()
            return false
        }
        return true

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}