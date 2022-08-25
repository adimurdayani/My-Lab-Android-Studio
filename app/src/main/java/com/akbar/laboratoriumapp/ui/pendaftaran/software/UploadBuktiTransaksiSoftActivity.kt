package com.akbar.laboratoriumapp.ui.pendaftaran.software

import android.app.Activity
import android.app.AlertDialog
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.result.contract.ActivityResultContracts
import com.akbar.laboratoriumapp.HomeActivity
import com.akbar.laboratoriumapp.R
import com.akbar.laboratoriumapp.core.remote.network.ApiConfig
import com.akbar.laboratoriumapp.core.remote.response.*
import com.akbar.laboratoriumapp.databinding.ActivityUploadBuktiTransaksiSoftBinding
import com.akbar.laboratoriumapp.util.SharedPref
import com.github.drjacky.imagepicker.ImagePicker
import com.squareup.picasso.Picasso
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class UploadBuktiTransaksiSoftActivity : AppCompatActivity() {
    private var _binding: ActivityUploadBuktiTransaksiSoftBinding? = null
    private val binding get() = _binding!!
    private lateinit var s: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityUploadBuktiTransaksiSoftBinding.inflate(layoutInflater)
        setContentView(binding.root)
        s = SharedPref(this)
        setDisplay()
    }

    private fun setDisplay() {
        binding.btnUpload.setOnClickListener {
            imagePick()
        }
    }

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val uri = it.data?.data!!
                // Use the uri to load the image
                Log.d("TAG", "URL Image: $uri")
                val fileUri: Uri = uri
                dialogUpload(File(fileUri.path))
            }
        }

    var alertDialog: AlertDialog? = null

    private fun dialogUpload(file: File) {
        val view = layoutInflater
        val layout = view.inflate(R.layout.upload_gambar, null)

        val imageView: ImageView = layout.findViewById(R.id.image)
        val btnUpload: LinearLayout = layout.findViewById(R.id.btn_upload)
        val btnGambar: LinearLayout = layout.findViewById(R.id.btn_gambarlain)

        Picasso.get()
            .load(file)
            .into(imageView)

        btnUpload.setOnClickListener {
            simpan(file)
        }

        btnGambar.setOnClickListener {
            imagePick()
        }
        alertDialog = AlertDialog.Builder(this).create()
        alertDialog!!.setView(layout)
        alertDialog!!.setCancelable(true)
        alertDialog!!.show()
    }


    fun File?.toMultipartBody(name: String = "img_transaksi"): MultipartBody.Part? {
        if (this == null) return null
        val reqFile: RequestBody = this.asRequestBody("image/*".toMediaTypeOrNull())
        return MultipartBody.Part.createFormData(name, this.name, reqFile)
    }

    private fun simpan(file: File) {
        if (s.getStatusLogin()) {
            val user = s.getUser()!!
            binding.progresBar.visibility = View.VISIBLE
            val fileImage = file.toMultipartBody()
            ApiConfig.instanceRetrofit.uploadTransaksiSoft(user.nim, fileImage!!)
                .enqueue(object : Callback<ResponseModel> {
                    override fun onResponse(
                        call: Call<ResponseModel>,
                        response: Response<ResponseModel>,
                    ) {
                        if (response.isSuccessful) {
                            binding.progresBar.visibility = View.GONE
                            showSuccess("Gambar berhasil diupload!")
                            pushAcitivty(HomeActivity::class.java)
                        } else {
                            binding.progresBar.visibility = View.GONE
                            showError(response.getErrorBody()?.message ?: "Error default")
                        }
                    }

                    override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                        showError("Terjadi kesalahan jaringan!.")
                        Log.d("Response", "Error: " + t.message)
                    }
                })
        }
    }

    private fun imagePick() {
        ImagePicker.with(this)
            .crop()
            .maxResultSize(512, 512)
            .createIntentFromDialog { launcher.launch(it) }
    }
}