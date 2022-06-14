package com.akbar.laboratoriumapp.ui.jadwal

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.akbar.laboratoriumapp.core.model.Jadwal
import com.akbar.laboratoriumapp.core.model.Nilai
import com.akbar.laboratoriumapp.core.remote.network.ApiConfig
import com.akbar.laboratoriumapp.core.remote.response.ResponseModel
import com.akbar.laboratoriumapp.databinding.FragmentJadwalBinding
import com.akbar.laboratoriumapp.ui.jadwal.adapter.AdapterJadwal
import com.akbar.laboratoriumapp.ui.nilai.adapter.AdapterNilai
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentJadwal : Fragment() {

    private var _binding: FragmentJadwalBinding? = null
    private val binding get() = _binding!!
    private var listjadwal: ArrayList<Jadwal> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJadwalBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setDisplay()
        return root
    }

    private fun setDisplay() {
        val layoutManager = LinearLayoutManager(requireActivity())
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        binding.apply {
            rvData.setHasFixedSize(true)
            rvData.adapter = AdapterJadwal(requireActivity(), listjadwal)
            rvData.layoutManager = layoutManager

            swipeData.setOnRefreshListener {
                binding.shimmerLoading.startShimmer()
                binding.divContent.visibility = View.GONE
                setdatajadwal()
            }
            val adapter = AdapterJadwal(requireActivity(), listjadwal)
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

    private fun setdatajadwal() {
        binding.shimmerLoading.startShimmer()
        binding.swipeData.isRefreshing = true

        ApiConfig.instanceRetrofit.getlistjadwal().enqueue(object : Callback<ResponseModel> {
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
                    listjadwal = res.jadwal
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
        setdatajadwal()
    }

    override fun onPause() {
        binding.shimmerLoading.stopShimmer()
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}