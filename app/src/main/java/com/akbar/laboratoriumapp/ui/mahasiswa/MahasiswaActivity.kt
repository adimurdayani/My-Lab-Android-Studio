package com.akbar.laboratoriumapp.ui.mahasiswa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.akbar.laboratoriumapp.HomeActivity
import com.akbar.laboratoriumapp.core.model.Mahasiswa
import com.akbar.laboratoriumapp.core.model.Pendaftaran
import com.akbar.laboratoriumapp.core.remote.network.ApiConfig
import com.akbar.laboratoriumapp.core.remote.response.ResponseModel
import com.akbar.laboratoriumapp.core.remote.response.pushAcitivty
import com.akbar.laboratoriumapp.databinding.ActivityMahasiswaBinding
import com.akbar.laboratoriumapp.ui.mahasiswa.adapter.AdapterHardware
import com.akbar.laboratoriumapp.ui.mahasiswa.adapter.AdapterMahasiswa
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MahasiswaActivity : AppCompatActivity() {

    private var _binding: ActivityMahasiswaBinding? = null
    private val binding get() = _binding!!
    private var listmahasiswa: ArrayList<Mahasiswa> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMahasiswaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setDisplay()
        setButton()
    }

    private fun setButton() {
        binding.btnKembali.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }

    private fun setDisplay() {
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        binding.apply {
            rvDataHardware.setHasFixedSize(true)
            rvDataHardware.adapter = AdapterMahasiswa(this@MahasiswaActivity, listmahasiswa)
            rvDataHardware.layoutManager = layoutManager

            swipeData.setOnRefreshListener {
                binding.shimmerLoading.startShimmer()
                binding.divContent.visibility = View.GONE
                setdatamahasiswa()
            }
            val adapter = AdapterMahasiswa(this@MahasiswaActivity, listmahasiswa)
            searchData.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    adapter.getSearchData().filter(query)
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }

            })
        }

    }

    private fun setdatamahasiswa() {
        binding.shimmerLoading.startShimmer()
        binding.swipeData.isRefreshing = true

        ApiConfig.instanceRetrofit.getlistmahasiswa().enqueue(object : Callback<ResponseModel> {
            override fun onResponse(
                call: Call<ResponseModel>,
                response: Response<ResponseModel>,
            ) {
                if (response.isSuccessful) {
                    val res = response.body()!!
                    binding.shimmerLoading.stopShimmer()
                    binding.shimmerLoading.visibility = View.GONE
                    binding.divContent.visibility = View.VISIBLE
                    binding.swipeData.isRefreshing = false
                    listmahasiswa = res.mahasiswa
                    setDisplay()

                } else {
                    binding.swipeData.isRefreshing = false
                    binding.divContent.visibility = View.GONE
                    binding.shimmerLoading.startShimmer()
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                binding.shimmerLoading.startShimmer()
                binding.swipeData.isRefreshing = false
                Log.d("Response", "Error: " + t.message)
            }
        })
    }

    override fun onResume() {
        binding.shimmerLoading.startShimmer()
        super.onResume()
        setdatamahasiswa()
    }

    override fun onPause() {
        binding.shimmerLoading.stopShimmer()
        super.onPause()
    }
}