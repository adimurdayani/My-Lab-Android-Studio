package com.akbar.laboratoriumapp.core.remote.response

import android.R
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.akbar.laboratoriumapp.util.SharedPref
import com.google.gson.Gson
import com.google.gson.internal.Primitives
import com.google.gson.reflect.TypeToken
import com.shashank.sony.fancydialoglib.Animation
import com.shashank.sony.fancydialoglib.FancyAlertDialog
import es.dmoral.toasty.Toasty
import retrofit2.Response
import java.lang.reflect.Type


fun <T> T.toJson(): String {
    return Gson().toJson(this)
}

fun <T> String?.toModel(classOfT: Class<T>): T? {
    if (this == null) return null
    val obj = Gson().fromJson<Any>(this, classOfT as Type)
    return Primitives.wrap(classOfT).cast(obj)!!
}

inline fun <reified T> Gson.fromJson(json: String): T =
    fromJson(json, object : TypeToken<T>() {}.type)

fun <T> Response<T>.getErrorBody(): ErrorResponse? {
    return try {
        this.errorBody()?.string()?.let {
            Gson().fromJson<ErrorResponse>(it)
        }
    } catch (exception: Exception) {
        null
    }
}

fun <T, S> Response<T>.getErrorBody(classOfT: Class<S>): S? {
    return this.errorBody()?.string()?.let {
        it.toModel(classOfT)
    }
}

fun <T> Context.pushActivity(activity: Class<T>) {
    val i = Intent(applicationContext, activity)
    i.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
    i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    startActivity(i)
}

fun <T> Fragment.pushAcitivty(activity: Class<T>) {
    val i = Intent(requireActivity(), activity)
    i.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
    i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    startActivity(i)
}

fun <T> Fragment.logoutAlert(activity: Class<T>) {
    FancyAlertDialog.Builder
        .with(requireActivity())
        .setTitle("Keluar dari aplikasi!")
        .setBackgroundColor(Color.parseColor("#ce9214"))
        .setMessage("Apakah anda yakin ingin keluar dari aplikasi ?")
        .setNegativeBtnText("Tutup")
        .setPositiveBtnBackground(Color.parseColor("#FF4081"))
        .setPositiveBtnText("Keluar")
        .setNegativeBtnBackground(Color.parseColor("#b45700"))
        .setAnimation(Animation.POP)
        .isCancellable(true)
        .setIcon(R.drawable.ic_dialog_info, View.VISIBLE)
        .onPositiveClicked {
            val s = SharedPref(requireActivity())
            s.setStatusLogin(false)
            val i = Intent(requireActivity(), activity)
            i.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(i)
            Toasty.error(requireActivity(), "Logout sukses!", Toast.LENGTH_SHORT, true).show()
        }
        .onNegativeClicked { dialog: Dialog? ->
            dialog?.dismiss()
        }
        .build()
        .show()
}

fun Fragment.showError(pesan: String?) {
    Toasty.error(requireActivity(), pesan!!, Toast.LENGTH_SHORT, true).show()
}

fun Fragment.showSuccess(pesan: String?) {
    Toasty.success(
        requireActivity(), pesan!!, Toast.LENGTH_SHORT, true
    ).show()
}

fun Activity.showSuccess(pesan: String?) {
    Toasty.success(
        this, pesan!!, Toast.LENGTH_SHORT, true
    ).show()
}

fun Activity.showError(pesan: String?) {
    Toasty.error(this, pesan!!, Toast.LENGTH_SHORT, true).show()
}

fun <T> Activity.pushAcitivty(activity: Class<T>) {
    val i = Intent(this, activity)
    i.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
    i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    startActivity(i)
}

fun Fragment.infoAlert() {
    Toasty.info(
        requireActivity(),
        "Fitur ini masih dalam pengembangan ",
        Toast.LENGTH_SHORT,
        true
    ).show()
}

class ErrorResponse {
    val status = 0
    val message = ""
}

data class Status(
    val code: Int? = null,
    val description: String? = null
)