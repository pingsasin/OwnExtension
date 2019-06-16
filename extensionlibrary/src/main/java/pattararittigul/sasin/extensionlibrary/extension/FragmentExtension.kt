package pattararittigul.sasin.extensionlibrary.extension

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import pattararittigul.sasin.extensionlibrary.R

fun Fragment.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    requireActivity().toast(message)
}

fun Fragment.toast(@StringRes message: Int, duration: Int = Toast.LENGTH_SHORT) {
    requireActivity().toast(message)
}

fun Fragment.hideKeyboard() = (requireActivity() as AppCompatActivity).hideKeyboard()

fun Fragment.setKeyboardAlwaysHidden() = (requireActivity() as AppCompatActivity).setKeyboardAlwaysHidden()
