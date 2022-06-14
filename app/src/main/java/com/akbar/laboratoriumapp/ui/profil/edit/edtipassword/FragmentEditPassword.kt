package com.akbar.laboratoriumapp.ui.profil.edit.edtipassword

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.akbar.laboratoriumapp.core.remote.network.ApiConfig
import com.akbar.laboratoriumapp.core.remote.response.*
import com.akbar.laboratoriumapp.databinding.FragmentEditpasswordBinding
import com.akbar.laboratoriumapp.ui.auth.AuthActivity
import com.akbar.laboratoriumapp.util.SharedPref
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentEditPassword : Fragment() {

    private var _binding: FragmentEditpasswordBinding? = null
    private val binding get() = _binding!!

    private lateinit var s: SharedPref

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditpasswordBinding.inflate(inflater, container, false)
        val root: View = binding.root
        s = SharedPref(requireActivity())
        setButton()
        return root
    }

    private fun setButton() {
        binding.btnUpdate.setOnClickListener {
            if (validasi()){
                updatePassword()
            }
        }
    }

    private fun updatePassword() {
        val user = s.getUser()!!
        binding.apply {
            progreButton.visibility = View.VISIBLE
            tvUpdate.visibility = View.GONE
            ApiConfig.instanceRetrofit.ubah_password(
                user.id,
                edPassword.text.toString(),
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
                            showSuccess("Password berhasil diupdate!")
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