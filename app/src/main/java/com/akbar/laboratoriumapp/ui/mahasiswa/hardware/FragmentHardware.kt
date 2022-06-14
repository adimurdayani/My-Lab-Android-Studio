package com.akbar.laboratoriumapp.ui.mahasiswa.hardware

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.akbar.laboratoriumapp.core.model.Nilai
import com.akbar.laboratoriumapp.core.model.Pendaftaran
import com.akbar.laboratoriumapp.core.remote.network.ApiConfig
import com.akbar.laboratoriumapp.core.remote.response.ResponseModel
import com.akbar.laboratoriumapp.databinding.FragmentDataHardwareBinding
import com.akbar.laboratoriumapp.ui.mahasiswa.adapter.AdapterHardware
import com.akbar.laboratoriumapp.ui.nilai.adapter.AdapterNilai
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentHardware : Fragment() {

    private var _binding: FragmentDataHardwareBinding? = null
    private val binding get() = _binding!!
    private var listhardware: ArrayList<Pendaftaran> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDataHardwareBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setDisplay()
        return root
    }

    private fun setDisplay() {
        val layoutManager = LinearLayoutManager(requireActivity())
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        binding.apply {
            rvDataHardware.setHasFixedSize(true)
            rvDataHardware.adapter = AdapterHardware(requireActivity(), listhardware)
            rvDataHardware.layoutManager = layoutManager

            swipeData.setOnRefreshListener {
                binding.shimmerLoading.startShimmer()
                binding.divContent.visibility = View.GONE
                setdatanilai()
            }
            val adapter = AdapterHardware(requireActivity(), listhardware)
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

    private fun setdatanilai() {
        binding.shimmerLoading.startShimmer()
        binding.swipeData.isRefreshing = true

        ApiConfig.instanceRetrofit.getListHardware().enqueue(object : Callback<ResponseModel> {
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
                    listhardware = res.hardware
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
        setdatanilai()
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