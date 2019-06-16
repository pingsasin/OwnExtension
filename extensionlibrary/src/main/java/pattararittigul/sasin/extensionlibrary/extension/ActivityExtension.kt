package pattararittigul.sasin.extensionlibrary.extension

import android.content.Context
import android.content.Intent
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorInt
import androidx.annotation.IdRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import pattararittigul.sasin.extensionlibrary.R


fun AppCompatActivity.replaceFragmentNow(@IdRes containerId: Int, fragment: Fragment) {
    this.supportFragmentManager.transactNow {
        replace(containerId, fragment)
    }
}

fun AppCompatActivity.hideKeyboard() {
    if (currentFocus != null) {
        val inputMethodManager = getSystemService(
            Context
            .INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
    }
}

fun AppCompatActivity.replaceFragmentWithAnimation(@IdRes containerId: Int, fragment: Fragment) {
    this.supportFragmentManager
        .beginTransaction()
        .setCustomAnimations(
            R.anim.slide_in_right,
            R.anim.slide_out_left,
            R.anim.slide_in_left,
            R.anim.slide_out_right
        )
        .replace(containerId, fragment)
        .addToBackStack(this::class.java.simpleName)
        .commit()
}

fun AppCompatActivity.replaceFragmentWithAnimationNoBackStack(@IdRes containerId: Int, fragment: Fragment) {
    this.supportFragmentManager
        .beginTransaction()
        .setCustomAnimations(
            R.anim.slide_in_right,
            R.anim.slide_out_left,
            R.anim.slide_in_left,
            R.anim.slide_out_right
        )
        .replace(containerId, fragment)
        .commit()
}

private inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        action()
    }.commit()
}

private inline fun FragmentManager.transactNow(action: FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        action()
    }.commitNow()
}


infix fun AppCompatActivity.setStatusBarColor(@ColorInt color: Int) {
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    window.statusBarColor = color
}

fun AppCompatActivity.setKeyboardAlwaysHidden() {
    this.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
}

fun AppCompatActivity.displayErrorDialog(message: String = "something went wrong, please try again.") {
    AlertDialog.Builder(this).apply {
        setTitle("Sorry..")
        setMessage(message)
        setPositiveButton("Dismiss") { dialog, _ ->
            dialog.dismiss()
        }
        setCancelable(false)
    }.also {
        it.show()
    }
}

inline fun <reified T : AppCompatActivity?> AppCompatActivity.toActivity() {
    val intent = Intent(this, T::class.java)
    this.startActivity(intent)
}
