package com.akbar.laboratoriumapp.ui.auth.ui.lupapassword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import com.akbar.laboratoriumapp.R
import com.akbar.laboratoriumapp.core.model.User
import com.akbar.laboratoriumapp.core.remote.network.ApiConfig
import com.akbar.laboratoriumapp.core.remote.response.*
import com.akbar.laboratoriumapp.databinding.ActivityAuthBinding
import com.akbar.laboratoriumapp.databinding.ActivityUbahPasswordUserBinding
import com.akbar.laboratoriumapp.ui.auth.AuthActivity
import com.akbar.laboratoriumapp.ui.auth.ui.utama.FragmentUtama
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UbahPasswordUserActivity : AppCompatActivity() {

    private var _binding: ActivityUbahPasswordUserBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityUbahPasswordUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val extra = intent.getStringExtra("extra")
        val json = Gson().fromJson(extra, User::class.java)
        Log.d("response", "json: $json")
        setButton()
    }

    private fun setButton() {
        binding.btnKembali.setOnClickListener {
            pushAcitivty(AuthActivity::class.java)
        }
        binding.btnKirim.setOnClickListener {
            if (validasi()) {
                ubahPassword()
            }
        }
    }

    private fun ubahPassword() {
        val extra = intent.getStringExtra("extra")
        val json = Gson().fromJson(extra, User::class.java)
        binding.apply {
            progresBar.visibility = View.VISIBLE
            ApiConfig.instanceRetrofit.ubah_password(
                json.id,
                edPassword.text.toString(),
            )
                .enqueue(object :
                    Callback<ResponseModel> {
                    override fun onResponse(
                        call: Call<ResponseModel>,
                        response: Response<ResponseModel>
                    ) {
                        if (response.isSuccessful) {
                            progresBar.visibility = View.GONE
                            showSuccess("Password berhasil diubah!")
                            pushAcitivty(AuthActivity::class.java)
                        } else {
                            progresBar.visibility = View.GONE
                            showError(response.getErrorBody()?.message ?: "Error default")
                        }
                    }

                    override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                        progresBar.visibility = View.GONE
                        showError("Terjadi masalah jaringan")
                    }

                })
        }
    }

    private fun validasi(): Boolean {

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
}