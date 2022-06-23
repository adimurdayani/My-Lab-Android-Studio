package com.akbar.laboratoriumapp.core.remote.network

import com.akbar.laboratoriumapp.core.remote.response.ResponseAlamat
import com.akbar.laboratoriumapp.core.remote.response.ResponseModel
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<ResponseModel>

    @FormUrlEncoded
    @POST("login/register")
    fun register(
        @Field("nim") nim: String,
        @Field("nama") nama: String,
        @Field("email") email: String,
        @Field("phone") phone: String,
        @Field("password") password: String
    ): Call<ResponseModel>

    @FormUrlEncoded
    @POST("login/edit")
    fun edit_profil(
        @Field("id") id: Int,
        @Field("nim") nim: String,
        @Field("nama") nama: String,
        @Field("email") email: String,
        @Field("phone") phone: String,
        @Field("kelamin") kelamin: String
    ): Call<ResponseModel>

    @FormUrlEncoded
    @POST("login/ubahpassword")
    fun ubah_password(
        @Field("id") id: Int,
        @Field("password") nim: String
    ): Call<ResponseModel>

    @GET("province")
    fun getProvinsi(
        @Header("key") key: String
    ): Call<ResponseAlamat>

    @GET("city")
    fun getKota(
        @Header("key") key: String,
        @Query("province") province_id: String
    ): Call<ResponseAlamat>

    @GET("kat_prakt")
    fun getpraktikum(): Call<ResponseModel>

    @GET("kat_reg")
    fun getLab(): Call<ResponseModel>

    @FormUrlEncoded
    @POST("pendaftaran_hardware")
    fun createPendaftaranHardware(
        @Field("nama") nama: String,
        @Field("nim") nim: String,
        @Field("kelamin") kelamin: String,
        @Field("agama") agama: String,
        @Field("kategori_id") kategori_id: Int? = 0,
        @Field("kategori_lab") kategori_lab: Int? = 0,
        @Field("semester") semester: String,
        @Field("alamat") alamat: String,
        @Field("pekerjaan_ortu") pekerjaan_ortu: String,
        @Field("alamat_ortu") alamat_ortu: String,
        @Field("kabupaten") kabupaten: String,
        @Field("nama_ortu") nama_ortu: String,
        @Field("provinsi") provinsi: String
    ): Call<ResponseModel>

    @FormUrlEncoded
    @POST("pendaftaran_software")
    fun createPendaftaranSoftware(
        @Field("nama") nama: String,
        @Field("nim") nim: String,
        @Field("kelamin") kelamin: String,
        @Field("agama") agama: String,
        @Field("kategori_id") kategori_id: Int? = 0,
        @Field("kategori_lab") kategori_lab: Int? = 0,
        @Field("semester") semester: String,
        @Field("alamat") alamat: String,
        @Field("pekerjaan_ortu") pekerjaan_ortu: String,
        @Field("alamat_ortu") alamat_ortu: String,
        @Field("kabupaten") kabupaten: String,
        @Field("nama_ortu") nama_ortu: String,
        @Field("provinsi") provinsi: String
    ): Call<ResponseModel>

    @FormUrlEncoded
    @POST("login/email_user")
    fun getEmail(
        @Field("email") email: String
    ): Call<ResponseModel>

    @FormUrlEncoded
    @POST("nilai_s")
    fun getNIlaiSoftwareProfile(
        @Field("nim") email: String
    ): Call<ResponseModel>

    @FormUrlEncoded
    @POST("nilai_h")
    fun getNIlaiHardwareProfile(
        @Field("nim") email: String
    ): Call<ResponseModel>

    //
    @GET("pendaftaran_hardware")
    fun getListHardware(): Call<ResponseModel>

    //
    @GET("pendaftaran_software")
    fun getListSoftware(): Call<ResponseModel>

    //
    @GET("nilai_h")
    fun getnilaihardware(): Call<ResponseModel>

    //
    @GET("nilai_s")
    fun getnilaisoftware(): Call<ResponseModel>

    @GET("jadwal")
    fun getlistjadwal(): Call<ResponseModel>

    @GET("mahasiswa")
    fun getlistmahasiswa(): Call<ResponseModel>

    @GET("informasi")
    fun getlisinformasi(): Call<ResponseModel>
}